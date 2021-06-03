package pl.pk99.steganography.model;

import pl.pk99.steganography.model.data.ImageText;

public interface TextToImageEncoder {

    /**
     * Encodes given message in image pixels. Each bit of the message will be stored in the least significant bit of
     * next pixels.
     *
     * @param text          message to encode.
     * @param pixelsOfImage pixels of image.
     * @param offset        pixel index from which the message will be encoded
     */
    void encode(ImageText text, int[] pixelsOfImage, int offset);
}
