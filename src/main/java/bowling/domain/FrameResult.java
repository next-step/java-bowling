package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private static final int UN_SCORE = -1;

    private String displayScore;
    private int score;
    private int totalScore = -1;

    public FrameResult(String displayScore, int score) {
        this.displayScore = displayScore;
        this.score = score;
    }

    public int addTotalScore(int beforeTotalScore) {
        if (isUnScore()) {
            totalScore = score;
            return totalScore;
        }
        totalScore = score + beforeTotalScore;
        return totalScore;
    }

    private boolean isUnScore() {
        return score == UN_SCORE;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FrameResult)) { return false; }
        final FrameResult that = (FrameResult) o;
        return score == that.score &&
               Objects.equals(displayScore, that.displayScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayScore, score);
    }
}
