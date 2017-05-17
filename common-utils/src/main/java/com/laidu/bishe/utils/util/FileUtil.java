package com.laidu.bishe.utils.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File tools
 */
@Slf4j
public class FileUtil {
    private final static Map<String,String> contentTypeToSuffix = new HashMap<>();
    private final static Map<String,String> suffixToContentType = new HashMap<>();

    private final static String filetypeStr = file2String("filetype");

    static {
        JSONObject jsonObject = JSON.parseObject(filetypeStr);
        for(String key : jsonObject.keySet()){
            suffixToContentType.put(key,jsonObject.getString(key));
            contentTypeToSuffix.put(jsonObject.getString(key),key);
        }
    }

    /**
     * keep the byte array to a file
     */
    public static File getFileFromBytes(byte[] bytes, String outputFile) {
        File file;
        BufferedOutputStream stream = null;
        FileOutputStream fileOutputStream = null;
        try {
            file = new File(outputFile);
            fileOutputStream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fileOutputStream);
            stream.write(bytes);
            return file;
        } catch (IOException e) {
            log.error("the data stream error", e);
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                log.error("the data stream error", e);
            }
        }

    }

    /**
     * keep the byte array to a file
     */
    public static File writeFileFromResourcesIfNotExists(String filename, String tmpDir) {
        File file;
        FileOutputStream fileOutputStream = null;
        try {
            file = new File(String.format("%s/%s",tmpDir,filename.substring(filename.lastIndexOf("/") + 1)));
            if (!file.exists()) {
                synchronized (FileUtil.class) {
                    if (!file.exists()) {
                        fileOutputStream = new FileOutputStream(file);
                        IOUtils.copy(FileUtil.class.getResourceAsStream(filename), fileOutputStream);
                    }
                }
            }
            return file;
        } catch (IOException e) {
            log.error("the data stream error", e);
            return null;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                log.error("the data stream error", e);
            }
        }

    }

    /**
     * File into a byte array
     */
    public static byte[] getBytesFromFile(File file) {
        byte[] data;
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            if (file == null) {
                return null;
            }

            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }

            fileInputStream.close();
            byteArrayOutputStream.close();
            data = byteArrayOutputStream.toByteArray();
            return data;
        } catch (IOException e) {
            log.error("the data stream error", e);
            return null;
        } finally {
            safeClose(byteArrayOutputStream);
            safeClose(fileInputStream);
        }
    }

    public static String getFromFile(final String fileName){
        InputStream is = null;
        try {
            is = FileUtil.class.getResourceAsStream(fileName);

            return IOUtils.toString(is,"UTF-8");
        } catch (Exception e) {
            log.error("failed to get redission conf fileName = " + fileName,e);
        } finally {
            safeClose(is);
        }
        return null;
    }

    public static String getFileAsString(final String fileName){
        InputStream is = null;
        try {
            File file=new File(fileName);
            if (file.exists()) {
                is = new FileInputStream(file);
                return IOUtils.toString(is, "UTF-8");
            }
        } catch (Exception e) {
            log.error("failed to get redission conf fileName = " + fileName,e);
        } finally {
            safeClose(is);
        }
        return null;
    }

    public static void safeClose(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void safeClose(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 文本文件转换为指定编码的字符串
     * @return 转换后的字符串
     * @throws IOException
     */
    public static String file2String(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(filePath);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String data;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while((data = br.readLine())!=null){
                stringBuilder.append(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                isr.close();
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 文本文件转换为指定编码的字符串
     * @return 转换后的字符串
     * @throws IOException
     */
    public static List<String> file2StringList(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(filePath);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String data;
        List<String> list = new ArrayList<>();
        try {
            while((data = br.readLine())!=null){
                list.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                isr.close();
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return list;
    }

    public static void deteleFile(final String fileName){
        /**
         * step 1:get origin css file
         */
        File originFile = new File(fileName);
        if (originFile.exists()){
            originFile.delete();
        }
    }
    public static void writeFile(final String filePath, final String content) throws IOException {
        /**
         * step 1: write css file
         */
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(content.getBytes("UTF-8"));
        fileOutputStream.close();
    }

    public static void writeFile(final String filePath, final InputStream content) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(IOUtils.toByteArray(content));
        }finally {
            if (fileOutputStream != null){
                fileOutputStream.close();
            }
        }
    }

    /**
     * 获取文件后缀
     * @param contentType
     * @param defaultSuffix
     * @return
     */
    public static String getFileSuffix(String contentType,String defaultSuffix){
        return contentTypeToSuffix.containsKey(contentType) ? contentTypeToSuffix.get(contentType) : defaultSuffix;
    }

    public static String getContentTypeSuffix(String suffix){
        return suffixToContentType.get(suffix);
    }

    public static String getFileSuffix(String filename){
        return filename.substring(filename.lastIndexOf(".")+1);
    }

    /**
     * 文件全路径获取文件名
     * @param filename
     * @return
     */
    public static String getFilename(String filename){
        return filename.substring(filename.lastIndexOf("/")+1);
    }

    /**
     * 文件全路径获取文件名
     * @param filename
     * @return
     */
    public static String getFilenameWithOutSuffix(String filename){
        return filename.substring(filename.lastIndexOf("/")+1, filename.contains(".") ? filename.lastIndexOf(".") : filename.length());
    }

    /**
     * 如果目录不存在则创建目录
     * @param dir
     */
    public static void createDirWhenNotExists(String dir){
        File file =new File(dir);
        if (!file.isDirectory()) {
            boolean mkdir = file.mkdirs();
            if (!mkdir){
                throw new UnsupportedOperationException("create dir failed");
            }
        }
    }

    /**
     * 拷贝一批文件到指定目录
     * @param fileRootPath 待拷贝文件的根目录
     * @param files 待拷贝的文件
     * @param dir 目标目录
     */
    public static void copy(String fileRootPath, List<String> files, String dir) throws IOException {
        if (CollectionUtils.isEmpty(files)){
            return;
        }
        createDirWhenNotExists(dir);
        for(String filename : files){
            writeFile(String.format("%s/%s",dir,getFilename(filename)),new FileInputStream(String.format("%s/%s",fileRootPath,filename)));
        }
    }

    /**
     * 拷贝一批文件到指定目录
     * @param fileRootPath 待拷贝文件的根目录
     * @param files 待拷贝的文件,key 为新的文件名, value 为旧文件名
     * @param dir 目标目录
     */
    public static void copy(String fileRootPath, Map<String,String> files, String dir) throws IOException {
        if (CollectionUtils.isEmpty(files)){
            return;
        }
        createDirWhenNotExists(dir);
        for(String filename : files.keySet()){
            writeFile(String.format("%s/%s%s",dir, filename, getFileSuffix(files.get(filename))),new FileInputStream(String.format("%s/%s",fileRootPath,files.get(filename))));
        }
    }

    /**
     * base64 to inputstream
     * @param photoBase64 str
     * @return
     */
    public static byte[] base64StringToByte(String photoBase64){
        String imageDataBytes = photoBase64.replaceFirst("^.*,", "");
        return Base64.decodeBase64(imageDataBytes.getBytes());
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
