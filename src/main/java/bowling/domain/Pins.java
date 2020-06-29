package bowling.domain;

public class Pins {
    private int downPin;

    private Pins(int downPin) {
        this.downPin = downPin;
    }

    public static Pins init() {
        return new Pins(0);
    }

    public Pins bowl(int downPin) {
        validateRange(this.downPin + downPin);

        this.downPin += downPin;
        return this;
    }

    public int getDownPin() {
        return downPin;
    }

    private void validateRange(int downPin) {
        if (downPin < 0 || downPin > 10) {
            throw new IllegalArgumentException("투구 값이 유효 범위가 아닙니다.");
        }

    }


}
