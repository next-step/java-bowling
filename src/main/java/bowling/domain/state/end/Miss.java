package bowling.domain.state.end;

import bowling.domain.pin.PinExpression;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Miss extends End {
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss from(Pins firstPins) {
        return new Miss(firstPins, Pins.create(0));
    }

    public static Miss from(Pins firstPins, Pins secondPins) {
        if (!firstPins.isMiss(secondPins)) {
            throw new IllegalArgumentException("End State Miss 생성 오류");
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score score() {
        return Score.ofMiss(firstPins.size() + secondPins.size());
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
    public boolean isMiss() {
        return true;
    }

    @Override
    public String symbol() {
        return PinExpression.convert(firstPins.size()) + OR + PinExpression.convert(secondPins.size());
    }
}
