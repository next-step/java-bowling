package bowling.domain.pin;

public enum PinStatus {
    STRIKE,
    SPARE,
    MISS;

    public static PinStatus plus(PinNo firstNo, PinNo secondNo) {
        if (firstNo.isMaxNo()) {
            return STRIKE;
        }
        if (firstNo.plus(secondNo).isMaxNo()) {
            return SPARE;
        }
        return MISS;
    }

    public boolean isMiss() {
        return this == MISS;
    }

    public boolean isSpare() {
        return this == SPARE;
    }
}
