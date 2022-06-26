package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Finished {
    private int preBowl;

    public Miss(int firstBowl, int secondBowl) {
        super(secondBowl, String.valueOf(secondBowl));
        this.preBowl = firstBowl;
    }

    @Override
    public Score getScore() {
        return new Score(fallenPins + preBowl, NO_MORE_CALCULATION);
    }
}
