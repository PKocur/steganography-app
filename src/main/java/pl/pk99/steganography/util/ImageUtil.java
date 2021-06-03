package pl.pk99.steganography.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static pl.pk99.steganography.config.Configuration.IMAGE_FORMAT;

public class ImageUtil {

    public static BufferedImage readImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    public static void saveImage(String newImageName, BufferedImage image, int[] pixelsOfImage) throws IOException {
        image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixelsOfImage, 0, image.getWidth());
        File outputFile = new File(newImageName);
        ImageIO.write(image, IMAGE_FORMAT, outputFile);
    }
}

