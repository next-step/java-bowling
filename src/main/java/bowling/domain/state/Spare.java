package bowling.domain.state;

import bowling.domain.Score;

public class Spare extends Finished {
    private static final String SPARE_SYMBOL = "/";

    private int preBowl;

    public Spare(int firstBowl, int secondBowl) {
        super(secondBowl, SPARE_SYMBOL);
        this.preBowl = firstBowl;
    }

    @Override
    public Score getScore() {
        return new Score(fallenPins + preBowl, CALCULATE_ONCE);
    }
}
