package bowling.domain.score;

import static bowling.domain.score.InProgressScore.ZERO;

public class InCalculableScore extends Score {
    protected InCalculableScore(int score) {
        super(score);
    }

    public static InCalculableScore init() {
        return new InCalculableScore(ZERO);
    }
}
