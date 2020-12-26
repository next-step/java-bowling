package bowling.domain;

public class Score {
    public static final int NOT_CALCULATED = -1;
    public static final Score NOT_SCORED = new Score(-1, 2) {
        @Override
        public boolean canBeCalculated(){ return true; }
    };

    private final int fallenPins;
    private final int leftTries;

    public Score(int fallenPins, int leftTries) {
        this.fallenPins = fallenPins;
        this.leftTries = leftTries;
    }

    public boolean canBeCalculated() {
        return leftTries == 0;
    }

    public int value() {
        return fallenPins;
    }

    public int getLeftTries() {
        return leftTries;
    }
}
