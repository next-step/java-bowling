package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private static final int UN_SCORE = -1;

    private String displayScore;
    private int score;
    private int totalScore = -1;

    public FrameResult(String displayScore) {
        this(displayScore, UN_SCORE);
    }

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

    public boolean isUnScore() {
        return score == UN_SCORE;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getDisplayScore() {
        return displayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FrameResult)) { return false; }
        final FrameResult that = (FrameResult) o;
        return score == that.score &&
               getTotalScore() == that.getTotalScore() &&
               Objects.equals(getDisplayScore(), that.getDisplayScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDisplayScore(), score, getTotalScore());
    }
}
