package score;

import java.util.Objects;

public class ScoreInfo {
    private final Score score;
    private final Status status;

    public static ScoreInfo firstScore(int score) {
        return new ScoreInfo(new Score(score), Status.findStatus(score, score));
    }

    public ScoreInfo nextScore(int score) {
        return new ScoreInfo(new Score(score), Status.findStatus(score, this.score.getScore() + score));
    }

    ScoreInfo(Score score, Status status) {
        this.score = score;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreInfo scoreInfo = (ScoreInfo) o;
        return Objects.equals(score, scoreInfo.score) &&
                status == scoreInfo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status);
    }
}
