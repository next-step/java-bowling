package bowling.domain;

public class BowlResult {
    private static final String SEPARATOR = "|";
    private static final String SPARE_NOTATION = "/";
    private static final String GUTTER_NOTATION = "-";
    private static final String STRIKE_NOTATION = "X";

    private String result;
    private int downPin;

    private BowlResult(int downPin) {
        valid(downPin);
        this.result = makeResult(downPin);
        this.downPin = downPin;
    }

    public static BowlResult of(int pinCount) {
        return new BowlResult(pinCount);
    }

    public BowlResult next(int downPin) {
        this.result = makeNextResult(downPin);
        this.downPin += downPin;
        valid(this.downPin);
        return this;
    }

    public String getResult() {
        return result;
    }

    private void valid(int downPin) {
        if (downPin < 0 || downPin > 10) {
            throw new IllegalArgumentException("유효 범위가 아닙니다.");
        }
    }

    private String makeResult(int downPin) {
        if (isStrike(downPin)) {
            return STRIKE_NOTATION;
        }
        if (isGutter(downPin)) {
            return GUTTER_NOTATION;
        }
        return String.valueOf(downPin);
    }

    private String makeNextResult(int downPin) {
        if (isSpare(downPin)) {
            return this.result + SEPARATOR + SPARE_NOTATION;
        }
        if (isGutter(downPin)) {
            return this.result + SEPARATOR + GUTTER_NOTATION;
        }
        return this.result + SEPARATOR + downPin;
    }


    public boolean isStrike(int downPin) {
        return downPin == 10;
    }

    private boolean isSpare(int downPin) {
        return this.downPin + downPin == 10;
    }

    private boolean isGutter(int downPin) {
        return downPin == 0;
    }


}
