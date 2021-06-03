package pl.pk99.steganography.model;

public interface MessageDecoder {

    /**
     * Decodes message from the image pixels.
     *
     * @param pixelsOfImage pixels of image.
     * @return bytes array of the decoded message
     */
    byte[] decodeFromImage(int[] pixelsOfImage);

    /**
     * Decrypts the message with a key.
     *
     * @param encryptedMessage message to decrypt.
     * @param key              key to decrypt the message.
     * @return decrypted message
     */
    String decrypt(String encryptedMessage, String key);
}
