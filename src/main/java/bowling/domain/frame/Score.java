package bowling.domain.frame;

import java.util.Objects;

public class Score {
    private int score;
    private boolean needAdditionalScore;
    private Score additionalScore;

    public Score(int score, boolean needAdditionalScore) {
        this.score = score;
        this.needAdditionalScore = needAdditionalScore;
    }

    public Score(int score) {
        this(score, false);
    }

    public int getScore() {
        if (Objects.isNull(additionalScore)) {
            return score;
        }
        return score + additionalScore.score;
    }

    public void setAdditionalScore(Score additionalScore) {
        this.additionalScore = additionalScore;
    }

    public boolean isSatisfied() {
        if (needAdditionalScore && !Objects.isNull(additionalScore)) {
            return true;
        }
        return !needAdditionalScore;
    }

    public Score add(Score score) {
        return new Score(this.score + score.score);
    }
}
