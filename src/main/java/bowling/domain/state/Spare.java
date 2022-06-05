package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Spare extends Finished {
    private static final int MAX_PINS = 10;
    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validate(Pins firstPins, Pins secondPins) {
        if(firstPins.sum(secondPins) != MAX_PINS) {
            throw new IllegalArgumentException("총 쓰러진 볼링 핀의 합이 10 이 아닙니다.");
        }
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public String expression() {
        return firstPins.expression() + "|/";
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        beforeScore = beforeScore.bowl(firstPins.getPins());
        if(beforeScore.isCalculable()) {
            return beforeScore;
        }
        beforeScore = beforeScore.bowl(secondPins.getPins());
        return beforeScore;
    }
}
