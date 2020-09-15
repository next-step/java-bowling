package dto;

public class FalledPinsDTO implements DTO {

    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private final int falledPins;

    private FalledPinsDTO(int falledPins) {
        this.falledPins = falledPins;
    }

    public int getFalledPins() {
        return falledPins;
    }

    public static void validate(int falledPins) throws IllegalArgumentException {
        if (falledPins < MIN_PINS || falledPins > MAX_PINS) {
            throw new IllegalArgumentException("입력된 핀 갯수가 범위를 벗어났습니다.");
        }
    }

    public static FalledPinsDTO of(int falledPins) {
        validate(falledPins);
        return new FalledPinsDTO(falledPins);
    }
}
