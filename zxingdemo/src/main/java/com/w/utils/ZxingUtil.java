package com.w.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @author yanmiao.wu
 * @create 2022-09-15 15:00
 */
public class ZxingUtil {

    public static void main(String[] args) throws WriterException, IOException {
//        String qrCodeText = "https://www.journaldev.com";
        //汉字扫描出的结果乱码
//        System.out.println(new String("汉字真是离谱".getBytes("GB2312"), "ISO-8859-1"));

        String qrCodeText = "疫情防控核酸码";
        String filePath = "JD.png";
        int size = 125;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println("DONE");
    }

    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        BitMatrix byteMatrix = qrCodeWriter.encode(new String(qrCodeText.getBytes("UTF-8"),"ISO-8859-1"), BarcodeFormat.QR_CODE, size, size, hintMap);

/*
        试试解决中文乱码问题
        matrix = new MultiFormatWriter().encode(new String(content.getBytes("UTF-8"),"ISO-8859-1"),
                BarcodeFormat.QR_CODE, newsize, newsize);*/
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }

}
