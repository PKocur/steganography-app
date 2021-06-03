package pl.pk99.steganography.model;

public interface MessageEncoder {

    /**
     * Encodes message into the given image pixels.
     *
     * @param message       message to encode.
     * @param pixelsOfImage pixels of image.
     */
    void encodeToImage(String message, int[] pixelsOfImage);

    /**
     * Encrypts the message with a key.
     *
     * @param message message to encrypt.
     * @param key     key to encrypt the message.
     * @return encoded message.
     */
    String encrypt(String message, String key);
}
