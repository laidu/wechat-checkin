package com.laidu.bishe.utils.util;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;
/**
 * 二维码生成工具，支持 保存到磁盘或输出流
 * Created by xueyunlong on 17-4-12.
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    public QRCodeUtil() throws Exception {
    }

    /**
     * 生成二维码
     * @param content    源内容
     * @param imgPath    生成二维码保存的路径
     * @param needCompress    是否要压缩
     * @return        返回二维码图片
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 在生成的二维码中插入图片
     * @param source
     * @param imgPath
     * @param needCompress
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成带logo二维码，并保存到磁盘
     * @param content
     * @param imgPath    logo图片
     * @param destPath
     * @param needCompress
     * @throws Exception
     */

    /**
     * 生成带logo二维码，并保存到磁盘
     *
     * @param content
     * @param imgPath    logo图片
     * @param destPath
     * @param needCompress 是否压缩
     * @throws Exception
     * @author lifq
     *
     * 2016年12月13日  上午9:54:49
     */
    public static File encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        String fileName = new Random().nextInt(99999999) + ".jpg";//生成随机文件名
        File imgFile = new File(destPath + "/" + fileName);
        ImageIO.write(image, FORMAT_NAME, imgFile);
        return imgFile;
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir。(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
    /**
     * 生成二维码并保存到磁盘目录
     *
     * @author lifq
     *
     * 2016年12月13日  上午9:56:42
     */
    public static File encode(String content, String imgPath, String destPath) throws Exception {
        return QRCodeUtil.encode(content, imgPath, destPath, false);
    }
    /**
     * 生成二维码并保存到磁盘目录
     *
     * @author lifq
     *
     * 2016年12月13日  上午9:56:42
     */
    public static File encode(String content, String destPath, boolean needCompress) throws Exception {
        return QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    /**
     * 生成二维码并保存到磁盘目录
     *
     * @author lifq
     *
     * 2016年12月13日  上午9:56:42
     */
    public static File encode(String content, String destPath) throws Exception {
        return QRCodeUtil.encode(content, null, destPath, false);
    }

    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * 从二维码中，解析数据
     * @param file    二维码图片文件
     * @return     返回从二维码中解析到的数据值
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }
    /**
     * 从二维码中，解析数据
     * @param image    二维码图片缓存
     * @return     返回从二维码中解析到的数据值
     * @throws Exception
     */
    public static String decode(BufferedImage image) throws Exception {

        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    /**
     * LuminanceSource这类层次结构的目的是不同的位图实现跨平台为请求获得灰度亮度值的标准接口。该接口只提供了抽象方法，
     * 因此可以生成和旋转创建副本。这是为了确保一个读者不修改原来的亮度源，并让它在一个未知的状态，在链中的其他读者。
     * https://zxing.github.io/zxing/apidocs/com/google/zxing/LuminanceSource.html
     * @author ljheee
     *
     */
    public static class BufferedImageLuminanceSource extends LuminanceSource {

        private final BufferedImage image;
        private final int left;
        private final int top;

        public BufferedImageLuminanceSource(BufferedImage image) {
            this(image, 0, 0, image.getWidth(), image.getHeight());
        }

        /**
         * 构造方法
         * @param image
         * @param left
         * @param top
         * @param width
         * @param height
         */
        public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
            super(width, height);

            int sourceWidth = image.getWidth();
            int sourceHeight = image.getHeight();
            if (left + width > sourceWidth || top + height > sourceHeight) {
                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
            }

            for (int y = top; y < top + height; y++) {
                for (int x = left; x < left + width; x++) {
                    if ((image.getRGB(x, y) & 0xFF000000) == 0) {
                        image.setRGB(x, y, 0xFFFFFFFF); // = white
                    }
                }
            }

            this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
            this.image.getGraphics().drawImage(image, 0, 0, null);
            this.left = left;
            this.top = top;
        }

        @Override
        public byte[] getRow(int y, byte[] row) {//从底层平台的位图提取一行（only one row）的亮度数据值
            if (y < 0 || y >= getHeight()) {
                throw new IllegalArgumentException("Requested row is outside the image: " + y);
            }
            int width = getWidth();
            if (row == null || row.length < width) {
                row = new byte[width];
            }
            image.getRaster().getDataElements(left, top + y, width, 1, row);
            return row;
        }

        @Override
        public byte[] getMatrix() {///从底层平台的位图提取亮度数据值
            int width = getWidth();
            int height = getHeight();
            int area = width * height;
            byte[] matrix = new byte[area];
            image.getRaster().getDataElements(left, top, width, height, matrix);
            return matrix;
        }

        @Override
        public boolean isCropSupported() {//是否支持裁剪
            return true;
        }

        /**
         * 返回一个新的对象与裁剪的图像数据。实现可以保存对原始数据的引用，而不是复制。
         */
        @Override
        public LuminanceSource crop(int left, int top, int width, int height) {
            return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);
        }

        @Override
        public boolean isRotateSupported() {//是否支持旋转
            return true;
        }

        @Override
        public LuminanceSource rotateCounterClockwise() {//逆时针旋转图像数据的90度，返回一个新的对象。
            int sourceWidth = image.getWidth();
            int sourceHeight = image.getHeight();
            AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);
            BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = rotatedImage.createGraphics();
            g.drawImage(image, transform, null);
            g.dispose();
            int width = getWidth();
            return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);
        }
    }

    public static void main(String[] args) throws Exception {
        //生成带logo 的二维码
        String text = "http://www.cnblogs.com/haha12";
//        File file = QRCodeUtil.encode(text, "/data/1.jpg", "d:/data", true);
//        System.out.println("生成二维码名称："+file.getName());
        File file = new File("/data/22.webp");

        String res = QRCodeUtil.decode(file);
        System.out.println("解析的二维码内容："+res);
    }
}
