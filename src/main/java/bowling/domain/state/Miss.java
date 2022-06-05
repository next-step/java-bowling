package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Miss extends Finished {
    private Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validate(Pins firstCountOfPins, Pins secondCountOfPins) {
        if (firstCountOfPins.sum(secondCountOfPins) > 10) {
            throw new IllegalArgumentException("핀의 합은 10개가 넘을 수 없습니다.");
        }
    }

    @Override
    public Score getScore() {
        return new Score(firstPins.sum(secondPins),0);
    }

    @Override
    public String expression() {
        return this.firstPins.expression(this.secondPins);
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
