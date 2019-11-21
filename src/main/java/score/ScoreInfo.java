package score;

import java.util.Objects;

public class ScoreInfo {
    private static final int INIT_SCORE = -1;
    private final Score score;
    private final Status status;

    public ScoreInfo(int score, Status status) {
        this.score = new Score(score);
        this.status = status;
    }

    public static ScoreInfo firstScore(int score) {
        return new ScoreInfo(score, Status.findStatus(score, INIT_SCORE));
    }

    public ScoreInfo nextScore(int score) {
        return new ScoreInfo(score, Status.findStatus(score, this.score.getScore()));
    }

    public boolean isStrike() {
        return status.equals(Status.STRIKE);
    }

    public boolean isSpare() {
        return status.equals(Status.SPARE);
    }

    public Status getStatus() {
        return status;
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

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "score=" + score +
                ", status=" + status +
                '}';
    }

    public Integer getScore() {
        return score.getScore();
    }
}
