package pl.pk99.steganography.model.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ImageText {

    private final int[] text;
    private final int bitSize;

    public ImageText(byte[] text, int bitSize) {
        this.text = asIntArray(text);
        this.bitSize = bitSize;
    }

    public ImageText(String text) {
        this.text = asIntArray(text.toCharArray());
        this.bitSize = 16;
    }

    private static int[] asIntArray(char[] charArray) {
        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = charArray[i];
        }
        return intArray;
    }

    private static int[] asIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            intArray[i] = byteArray[i];
        }
        return intArray;
    }
}
