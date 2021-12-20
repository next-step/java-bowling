package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Spare extends End {
    private Pins firstPins;
    private Pins secondPins;

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
    Score score() {
        return Score.ofSpare();
    }

    @Override
    Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.next(firstPins.size());
        if (score.calculated()) {
            return score;
        }
        return score.next(secondPins.size());
    }

    @Override
    String symbol() {
        return firstPins.size() + OR + SPARE_SYMBOL;
    }
}
