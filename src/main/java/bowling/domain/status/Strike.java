package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Strike extends Finished {

    public static final int STRIKE_SCORE = 10;

    @Override
    public Score getScore() {
        return new Score(STRIKE_SCORE, 2);
    }

    @Override
    public Score addScore(Score score) {
        return new Score(score.getScore() + STRIKE_SCORE, score.getNextScoreCnt() - 1);
    }
}
