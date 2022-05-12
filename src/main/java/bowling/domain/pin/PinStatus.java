package bowling.domain.pin;

public enum PinStatus {
    STRIKE,
    SPARE,
    MISS;

    public static PinStatus getStatus(PinNo firstNo, PinNo secondNo) {
        if (firstNo.isMaxNo()) {
            return STRIKE;
        }
        if (firstNo.plus(secondNo).isMaxNo()) {
            return SPARE;
        }
        return MISS;
    }
}
