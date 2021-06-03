package pl.pk99.steganography.model.impl;

import com.grayen.encryption.caesar.algorithm.Caesar;
import com.grayen.encryption.caesar.algorithm.implementation.CaesarFabric;
import pl.pk99.steganography.model.MessageEncoder;
import pl.pk99.steganography.model.TextToImageEncoder;
import pl.pk99.steganography.model.data.ImageText;
import pl.pk99.steganography.model.data.enumerator.ImageColorType;

import java.nio.ByteBuffer;

public class SimpleMessageEncoder implements MessageEncoder {

    @Override
    public void encodeToImage(String message, int[] pixelsOfImage) {
        byte[] messageLength = ByteBuffer.allocate(4).putInt(message.length()).array();
        TextToImageEncoder textToImageEncoder = new TextToImageEncoderImpl(ImageColorType.RGB);
        ImageText lengthText = new ImageText(messageLength, 8);
        textToImageEncoder.encode(lengthText, pixelsOfImage, 0);
        ImageText text = new ImageText(message);
        textToImageEncoder.encode(text, pixelsOfImage, 32);
    }

    @Override
    public String encrypt(String message, String key) {
        Caesar encryption = CaesarFabric.getEncryptionSystem();
        return encryption.encrypt(message, key);
    }
}
