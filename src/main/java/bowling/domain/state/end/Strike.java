package bowling.domain.state.end;

import bowling.domain.Score;

import static bowling.domain.Pins.PINS_MAX_COUNT;

public class Strike extends End {

    private Strike() {
    }

    public static Strike from() {
        return new Strike();
    }

    @Override
    public Score score() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        if (beforeScore.calculated()) {
            return beforeScore;
        }
        return beforeScore.next(PINS_MAX_COUNT);
    }

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }
}
