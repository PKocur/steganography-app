package pl.pk99.steganography.model;

public interface TextFromImageDecoder {

    /**
     * Decodes the message encoded in image pixels, assuming that each bit of the encoded message is stored in the
     * least significant bit of next pixels.
     *
     * @param pixelsOfImage pixels of image.
     * @param offset        first pixel of the encoded message.
     * @param end           last pixel of the encoded message.
     * @return bytes of the decoded message.
     */
    byte[] decode(int[] pixelsOfImage, int offset, int end);
}
