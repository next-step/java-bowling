package bowling.domain.state.end;

import bowling.domain.pin.PinExpression;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Spare extends End {
    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Spare of(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException("End State Spare 생성 오류");
        }
        return new Spare(firstPins, secondPins);
    }

    @Override
    public Score score() {
        return Score.ofSpare();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.next(firstPins.size());
        if (score.calculated()) {
            return score;
        }
        return score.next(secondPins.size());
    }

    @Override
    public String symbol() {
        return PinExpression.convert(firstPins.size()) + OR + SPARE_SYMBOL;
    }
}
