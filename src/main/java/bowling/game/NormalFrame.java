package bowling.game;

public class NormalFrame implements Frame {
    private static final int MAX_PINS_OF_FRAME = 10;

    private final PinScore first;
    private PinScore second;

    public NormalFrame(PinScore first) {
        this.first = first;
    }

    @Override
    public void pitch(PinScore second) {
        validateState();
        validatePins(second);

        this.second = second;
    }

    @Override
    public boolean isEnd() {
        if (isStrike()) {
            return true;
        }

        return (null != this.second);
    }

    @Override
    public String toString() {
        if (!isEnd()) {
            return this.first.toString();
        }

        if (isStrike()) {
            return "X";
        }

        if (isSpare()) {
            return this.first + "|/";
        }

        return this.first + "|" + this.second;
    }

    boolean isMiss() {
        if (!isEnd()) {
            return false;
        }

        if (isStrike()) {
            return false;
        }

        return (MAX_PINS_OF_FRAME != this.first.sumPins(this.second));
    }

    boolean isSpare() {
        if (!isEnd()) {
            return false;
        }

        if (isStrike()) {
            return false;
        }

        return (MAX_PINS_OF_FRAME == this.first.sumPins(this.second));
    }

    boolean isStrike() {
        return this.first.isTen();
    }

    private void validateState() {
        if (isEnd()) {
            throw new IllegalStateException("This frame has been ended");
        }
    }

    private void validatePins(PinScore second) {
        int sumOfPins = this.first.sumPins(second);
        if (MAX_PINS_OF_FRAME < sumOfPins) {
            throw new IllegalStateException("Invalid pin score");
        }
    }
}
