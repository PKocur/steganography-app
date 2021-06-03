package pl.pk99.steganography.model.impl;

import lombok.RequiredArgsConstructor;
import pl.pk99.steganography.model.TextFromImageDecoder;
import pl.pk99.steganography.model.data.enumerator.ImageColorType;

import java.util.BitSet;

import static pl.pk99.steganography.util.BitUtil.getBit;

@RequiredArgsConstructor
public class TextFromImageDecoderImpl implements TextFromImageDecoder {

    private final ImageColorType colorType;

    @Override
    public byte[] decode(int[] pixelsOfImage, int offset, int end) {
        BitSet bitSet = new BitSet();
        int indexOfBitSet = 0;
        int indexOfPixel = offset;
        while (indexOfPixel < end) {
            indexOfPixel++;
            int pixel = pixelsOfImage[indexOfPixel];
            bitSet.set(indexOfBitSet++, getBit(pixel, colorType.getLeastSignificantBit()));
            if (indexOfPixel == end) {
                return bitSet.toByteArray();
            }
        }
        return bitSet.toByteArray();
    }
}
