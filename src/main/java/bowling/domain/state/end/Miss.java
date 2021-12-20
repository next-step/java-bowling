package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Miss extends End {
    private Pins firstPins;
    private Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss from(Pins secondPins) {
        return new Miss(Pins.create(0), secondPins);
    }

    public static Miss from(Pins firstPins, Pins secondPins) {
        if (!firstPins.isMiss(secondPins)) {
            throw new IllegalArgumentException("End State Miss 생성 오류");
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    Score score() {
        return Score.ofMiss(firstPins.size() + secondPins.size());
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
        return firstPins.size() + OR + secondPins.size();
    }
}
