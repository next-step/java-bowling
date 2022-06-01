package bowling.domain.state;

import static bowling.domain.ScoreSymbols.SCORE_GAP;
import static bowling.domain.ScoreSymbols.STRIKE_SYMBOL;

import bowling.domain.Score;

public class Strike implements State {
    private static final String CANNOT_THROW_AFTER_STRIKE = "스트라이크 이 후에는 볼을 더 던질 수 없음";

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException(CANNOT_THROW_AFTER_STRIKE);
    }

    @Override
    public Score addBonus(Score previousScore) {
        return Score.of(this.score(), previousScore);
    }

    @Override
    public Score score() {
        return Score.ofStrike();
    }

    @Override
    public String output() {
        return SCORE_GAP + STRIKE_SYMBOL + SCORE_GAP;
    }
}
