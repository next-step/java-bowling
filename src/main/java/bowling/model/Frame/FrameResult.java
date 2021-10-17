package bowling.model.frame;

import java.util.Objects;

public class FrameResult {
    private String desc;
    private int score;
    private int totalScore = -1;

    public FrameResult(String desc, int score) {
        this.desc = desc;
        this.score = score;
    }

    public int addTotalScore(int beforeTotalScore) {
        if (isUnScore()) {
            this.totalScore = this.score;
            return this.totalScore;
        }

        this.totalScore = this.score + beforeTotalScore;
        return this.totalScore;
    }

    boolean isUnScore() {
        return this.score == NormalFrame.UN_SCORE_STATE;
    }

    @Override
    public String toString() {
        return "FrameResult [desc=" + desc + ", score=" + score + ", totalScore=" + totalScore + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResult that = (FrameResult) o;
        return score == that.score && totalScore == that.totalScore && Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, score, totalScore);
    }
}
