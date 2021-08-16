package bowling.bowling;

public class Pins {

    private static final int MAX_PINS_COUNT = 10;
    private static final int BOWL_COUNT = 1;
    private static final int MAX_BOWL_COUNT = 2;

    private final int first;
    private int last;
    private int bowlCount;

    public Pins(int first) {
        this.first = first;
        this.bowlCount = BOWL_COUNT;
    }

    public void bowl(int bowl) {
        last = bowl;
        bowlCount++;
    }

    public boolean isStrike() {
        return MAX_PINS_COUNT == first && bowlCount == BOWL_COUNT;
    }

    public boolean isSpare() {
        return MAX_PINS_COUNT == first + last && bowlCount == MAX_BOWL_COUNT;
    }

    public boolean isMiss() {
        return MAX_PINS_COUNT > first + last && first + last != 0;
    }

    public boolean isGutter() {
        return first + last == 0;
    }
}
