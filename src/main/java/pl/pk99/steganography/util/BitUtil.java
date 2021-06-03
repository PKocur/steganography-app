package pl.pk99.steganography.util;

public class BitUtil {

    public static int setBit(int number, int index) {
        return number | 1 << index;
    }

    public static int setBit(int number, int index, boolean value) {
        if (value) {
            return setBit(number, index);
        } else {
            return number & ~(1 << index);
        }
    }

    public static boolean getBit(int number, int index) {
        return (number & (1 << index)) > 0;
    }
}
