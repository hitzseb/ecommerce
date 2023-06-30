package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    public byte[] convertImageToByteArray(MultipartFile imageFile) {
        try {
            BufferedImage resizedImage = ImageUtils.resizeImage(imageFile);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MultipartFile getDefaultImage() throws IOException {
        String imagePath = "src/main/resources/static/default.jpg";
        Path path = Paths.get(imagePath);

        byte[] fileBytes = Files.readAllBytes(path);

        return new MultipartFile() {
            @Override
            public String getName() {
                return "default.png";
            }

            @Override
            public String getOriginalFilename() {
                return "default.png";
            }

            @Override
            public String getContentType() {
                return "image/png";
            }

            @Override
            public boolean isEmpty() {
                return fileBytes.length == 0;
            }

            @Override
            public long getSize() {
                return fileBytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileBytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(fileBytes);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), fileBytes);
            }
        };
    }

}
