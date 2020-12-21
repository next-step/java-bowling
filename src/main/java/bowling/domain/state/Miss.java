package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Result {

    public Miss(Pins pins, int totalCount) {
        super(pins);
        score = Score.getMissScore(totalCount);
    }

    @Override
    public Score addNextScore(Score score) {
        score = addNextScore(score, pins.getFirstFall());
        return addNextScore(score, pins.getSecondFall());
    }
}
