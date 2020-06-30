package bowling.domain;

import java.util.stream.Stream;

public enum PitchingType {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static PitchingType getPitchingType(FallenPinNumber fallenPinNumber) {
        return Stream.of(PitchingType.values()).filter(type -> type.extract(fallenPinNumber))
    }

    public abstract boolean extract(FallenPinNumber fallenPinNumber);
}
