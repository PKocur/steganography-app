package pl.pk99.steganography.model.impl;

import lombok.RequiredArgsConstructor;
import pl.pk99.steganography.model.TextToImageEncoder;
import pl.pk99.steganography.model.data.ImageText;
import pl.pk99.steganography.model.data.enumerator.ImageColorType;

import static pl.pk99.steganography.util.BitUtil.getBit;
import static pl.pk99.steganography.util.BitUtil.setBit;

@RequiredArgsConstructor
public class TextToImageEncoderImpl implements TextToImageEncoder {

    private final ImageColorType colorType;

    @Override
    public void encode(ImageText text, int[] pixelsOfImage, int offset) {
        int indexOfPixel = offset;
        for (int s : text.getText()) {
            for (int j = 0; j < text.getBitSize(); j++) {
                indexOfPixel++;
                int pixel = pixelsOfImage[indexOfPixel];
                pixelsOfImage[indexOfPixel] = setBit(pixel, colorType.getLeastSignificantBit(), getBit(s, j));
            }
        }
    }
}
