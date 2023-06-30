package com.hitzseb.ecommerce.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {
    public static final int MAX_IMAGE_WIDTH = 332;
    public static final int MAX_IMAGE_HEIGHT = 193;

    public static BufferedImage resizeImage(MultipartFile imageFile) throws IOException {
        // Leer la imagen original
        BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

        // Obtener las dimensiones originales de la imagen
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // Verificar si la imagen ya cumple con las dimensiones máximas
        if (originalWidth <= MAX_IMAGE_WIDTH && originalHeight <= MAX_IMAGE_HEIGHT) {
            return originalImage; // Devolver la imagen original sin cambios
        }

        // Calcular las nuevas dimensiones de la imagen manteniendo la proporción
        int newWidth, newHeight;
        if (originalWidth > originalHeight) {
            newWidth = MAX_IMAGE_WIDTH;
            newHeight = (int) Math.round((double) originalHeight * MAX_IMAGE_HEIGHT / originalWidth);
        } else {
            newHeight = MAX_IMAGE_HEIGHT;
            newWidth = (int) Math.round((double) originalWidth * MAX_IMAGE_WIDTH / originalHeight);
        }

        // Redimensionar la imagen
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resizedImage;
    }

}
