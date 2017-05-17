package com.laidu.bishe.utils.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by huangpin on 16/7/21.
 */
public class ImageUtil {

    /**
     * 生成原图和缩略图
     */
    public static byte[] zoom(InputStream sourceFileIs, String suffix,
                                int width, int height) throws IOException {
        BufferedImage readImage = ImageIO.read(sourceFileIs);
        Image image = readImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage zoomImage = new BufferedImage(width, height, readImage.getType() == 0 ? BufferedImage.TYPE_3BYTE_BGR : readImage.getType());
        Graphics gc = zoomImage.getGraphics();
        gc.drawImage(image, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.flush();
        ImageIO.write(zoomImage, suffix, baos);
        return baos.toByteArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedImage readImage = ImageIO.read(new FileInputStream(new File("/Users/chenwen/tmp/231486637337_.pic.jpg")));

        System.out.println(readImage.getType());

        BufferedImage zoomImage = new BufferedImage(10, 10, readImage.getType());
    }
}
