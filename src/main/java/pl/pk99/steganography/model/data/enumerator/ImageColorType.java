package pl.pk99.steganography.model.data.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageColorType {
    RGB(0);

    private final int leastSignificantBit;
}
