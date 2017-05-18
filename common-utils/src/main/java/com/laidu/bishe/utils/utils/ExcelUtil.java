package com.laidu.bishe.utils.utils;

import com.alibaba.fastjson.JSONObject;
import com.laidu.bishe.utils.annotation.ColumnNum;
import com.laidu.bishe.utils.annotation.ColumnRowValue;
import com.laidu.bishe.utils.annotation.RowNum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenwen on 16/12/15.
 */
@Slf4j
public class ExcelUtil {

    public static Workbook create(InputStream in) throws
            IOException, InvalidFormatException {
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return new HSSFWorkbook(in);
        }
        if (POIXMLDocument.hasOOXMLHeader(in)) {
            return new XSSFWorkbook(OPCPackage.open(in));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }

    public static ExcelParseImportData parseImportData(Class<?> type, Sheet sheet) {
        Field[] declaredFields = type.getDeclaredFields();

        Map<Integer, Field> fieldMap = new HashMap<>();

        JSONObject initJson = new JSONObject();

        Field rowNum = null;

        int maxCol = 0;
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                if (columnNum.value() <= 0) {
                    throw new IllegalArgumentException(String.format("%s column num must more than 0, the column num start 1", field.getName()));
                }
                Integer num = columnNum.value() - 1;
                if (fieldMap.containsKey(num)) {
                    throw new IllegalArgumentException(String.format("%s and %s column num is duplicate", fieldMap.get(num).getName(), field.getName()));
                }
                fieldMap.put(num, field);

                if (num > maxCol) {
                    maxCol = num;
                }
            } else if (field.isAnnotationPresent(RowNum.class)) {
                rowNum = field;
            } else if (field.isAnnotationPresent(ColumnRowValue.class)) {
                ColumnRowValue columnRowValue = field.getAnnotation(ColumnRowValue.class);
                if (sheet.getLastRowNum() < columnRowValue.row() - 1) {
                    throw new IllegalArgumentException(String.format("%s row is not exists , at row = %s , col = %s", field.getName(), columnRowValue.row(), columnRowValue.col()));
                }
                Row row = sheet.getRow(columnRowValue.row() - 1);
                if (row.getLastCellNum() < columnRowValue.col() - 1) {
                    throw new IllegalArgumentException(String.format("%s col is not exists , at row = %s , col = %s", field.getName(), columnRowValue.row(), columnRowValue.col()));
                }
                initJson.put(field.getName(), getValue(field.getType(), row.getCell(columnRowValue.col() - 1)));
            }
        }

        if (fieldMap.size() == 0) {
            throw new IllegalArgumentException("please add ColumnNum annotation to class " + type.getTypeName());
        }

        ExcelParseImportData excelParseData = new ExcelParseImportData();
        excelParseData.setFieldMap(fieldMap);
        excelParseData.setInitJson(initJson);
        excelParseData.setMaxCol(maxCol);
        excelParseData.setRowNum(rowNum);
        return excelParseData;
    }

    public static Map<Integer, Field> parseExportData(Class<?> type) {
        Map<Integer, Field> fieldMap = new HashMap<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                Integer num = columnNum.value() - 1;
                fieldMap.put(num, field);
            }
        }
        return fieldMap;
    }

    public static Map<Integer,String> parseExportTitle(Class<?> type){
        Map<Integer, String> fieldMap = new HashMap<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                Integer num = columnNum.value() - 1;
                fieldMap.put(num, columnNum.title());
            }
        }
        return fieldMap;
    }

    public static String parseSnapshotData(String value, JSONObject snapshot) {
        String initValue = value;
        if (StringUtils.isEmpty(value)){
            return value;
        }
        Pattern pattern = Pattern.compile("\\$\\{([a-zA-Z0-9_,\\[\\]]+)\\}");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String key = matcher.group(1);
            if (snapshot.containsKey(key)) {
                value = value.replace("${" + key + "}", snapshot.getString(key));
            } else {
                value = value.replace("${" + key + "}", "NULL");
            }
        }
        if (initValue.equals(value)) {
            value = "NULL";
        }
        return value;
    }

    /**
     * 根据值的类型设置单元格的值
     *
     * @param cell  单元格
     * @param value 值
     */
    public static void setValue(HSSFCell cell, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Date) {
            cell.setCellValue(DateUtil.formatDateToString((Date) value));
        } else if (value instanceof Double) {
            cell.setCellValue(Double.parseDouble(ObjectUtil.getStrValue(value)));
        } else if (value instanceof Boolean) {
            cell.setCellValue(Boolean.parseBoolean(ObjectUtil.getStrValue(value)));
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(Integer.parseInt(ObjectUtil.getStrValue(value)));
        } else if (value instanceof Long) {
            cell.setCellValue(Long.parseLong(ObjectUtil.getStrValue(value)));
        } else {
            cell.setCellValue(ObjectUtil.getStrValue(value));
        }
    }


    /**
     * 根据值的类型设置单元格的值
     */
    public static Object getValue(Class<?> type, Cell cell) {
        try {
            if (type.isAssignableFrom(Date.class)) {
                return cell.getDateCellValue();
            } else if (type.isAssignableFrom(Double.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Double.parseDouble(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Boolean.class)) {
                return cell.getBooleanCellValue();
            } else if (type.isAssignableFrom(BigDecimal.class)) {
                try {
                    return new BigDecimal(cell.getNumericCellValue());
                } catch (Exception e) {
                    return new BigDecimal(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Integer.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Integer.parseInt(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Long.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Long.parseLong(cell.getStringCellValue());
                }
            }
        } catch (Exception e) {
            log.error("解析错误", e);
        }
        return getCellValue(cell);
    }

    @Data
    public static class ExcelParseImportData {
        private Map<Integer, Field> fieldMap;

        private JSONObject initJson;

        private Field rowNum;

        private int maxCol;
    }

    @Data
    public static class ExcelParseExportData {
        private Map<Integer, Field> fieldMap;

        private JSONObject initJson;

        private Field rowNum;

        private int maxCol;
    }

    public static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();

            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();

            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
        }

        return null;
    }
}
