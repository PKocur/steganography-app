package pl.pk99.steganography.model.impl;

import com.grayen.encryption.caesar.algorithm.Caesar;
import com.grayen.encryption.caesar.algorithm.implementation.CaesarFabric;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pk99.steganography.model.MessageDecoder;
import pl.pk99.steganography.model.TextFromImageDecoder;
import pl.pk99.steganography.model.data.enumerator.ImageColorType;

import java.nio.ByteBuffer;

@RequiredArgsConstructor
@Getter
public class SimpleMessageDecoder implements MessageDecoder {

    @Override
    public byte[] decodeFromImage(int[] pixelsOfImage) {
        TextFromImageDecoder textFromImageDecoder = new TextFromImageDecoderImpl(ImageColorType.RGB);
        byte[] messageLength = textFromImageDecoder.decode(pixelsOfImage, 0, 31);
        return textFromImageDecoder.decode(pixelsOfImage, 32, ByteBuffer.wrap(messageLength).getInt() * 16 + 32);
    }

    @Override
    public String decrypt(String encryptedMessage, String key) {
        Caesar encryption = CaesarFabric.getEncryptionSystem();
        return encryption.decrypt(encryptedMessage, key);
    }
}
