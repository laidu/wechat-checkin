package com.laidu.bishe.utils.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * File tools
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);

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
            logger.error("the data stream error", e);
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
                logger.error("the data stream error", e);
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
            logger.error("the data stream error", e);
            return null;
        } finally {
            safeClose(byteArrayOutputStream);
            safeClose(fileInputStream);
        }
    }

    /*
    * File inputstream into String
    * */
    public static String getFromInputStream(final InputStream inputStream){
        try {
            return IOUtils.toString(inputStream,"UTF-8");
        } catch (Exception e) {
            logger.error("failed to get redission conf fileName ");
        } finally {
            safeClose(inputStream);
        }
        return null;
    }


    /*
    * File into string
    * */

    public static String getFromFile(final String fileName){
        InputStream is = null;
        try {
            is = FileUtil.class.getResourceAsStream(fileName);

            return IOUtils.toString(is,"UTF-8");
        } catch (Exception e) {
            logger.error("failed to get redission conf fileName = " + fileName,e);
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
            logger.error("failed to get redission conf fileName = " + fileName,e);
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

    public static void deteleFile(final String fileName){
        /**
         * step 1:get origin css file
         */
        File originFile = new File(fileName);
        if (originFile != null){
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

}
