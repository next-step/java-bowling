package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Result {

    public static final int LEFT = 0;

    public Miss(Pins pins, int totalCount) {
        super(pins);
        score = new Score(totalCount, LEFT);
    }

    @Override
    public Score addNextScore(Score score) {
        score = addNextScore(score, pins.getFirstFall());
        return addNextScore(score, pins.getSecondFall());
    }
}
