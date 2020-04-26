package bowling.domain.State;

import bowling.domain.Score;

public class Strike extends Finished {
    @Override
    public String getDesc() {
        return STRIKE;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(MAX_PINS);
    }
}
