package com.neroimor;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        String data = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String filePath = "QRCode.png";
        generateQrCode(data, filePath);
    }

    public static void generateQrCode(String data, String filePath) throws Exception {
        //Устанавливаем параметры для генерации QR-кода
        Map<EncodeHintType,Object> hintMap = new HashMap<>();
        //Устанавливаем отступы вокруг QR-кода
        hintMap.put(EncodeHintType.MARGIN, 1);
        //Генерация QR кода
        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 400,400 ,hintMap);

        // Преобразуем BitMatrix в изображение
        BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                image.setRGB(i, j, matrix.get(i, j) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        saveImage(image, filePath);
    }


    public static void saveImage(BufferedImage image, String filePath) throws Exception {
        // Сохраняем изображение на диск
        ImageIO.write(image, "PNG", new File(filePath));
        System.out.println("QR-код успешно сгенерирован и сохранен в " + filePath);
    }
}