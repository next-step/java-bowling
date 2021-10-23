package bowling.domain.frame;

import java.util.Objects;

public class FrameResult {

    public static final int CANNOT_CALCULATED_SCORE_VALUE = -1;
    private static final String BLANK_SCORE_STRING = "";

    private int totalScore;
    private final int score;
    private final String desc;

    private FrameResult(int totalScore, int score, String desc) {
        this.totalScore = totalScore;
        this.score = score;
        this.desc = desc;
    }

    static FrameResult of(int totalScore, int score, String desc) {
        return new FrameResult(totalScore, score, desc);
    }

    public static FrameResult ofScoreAndDesc(int score, String desc) {
        return new FrameResult(CANNOT_CALCULATED_SCORE_VALUE, score, desc);
    }

    public static FrameResult createFrameResultByNoCaculatedScore(String desc) {
        return new FrameResult(CANNOT_CALCULATED_SCORE_VALUE, CANNOT_CALCULATED_SCORE_VALUE, desc);
    }

    public void addTotalScore(int beforeTotalScore) {
        if (score != CANNOT_CALCULATED_SCORE_VALUE) {
            this.totalScore = score + beforeTotalScore;
        }
    }

    public int score() {
        return score;
    }

    public String desc() {
        return desc;
    }

    public int totalScore() {
        return totalScore;
    }

    public String totalScoreToString() {
        if (totalScore == CANNOT_CALCULATED_SCORE_VALUE) {
            return BLANK_SCORE_STRING;
        }
        return String.valueOf(totalScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult result = (FrameResult) o;
        return score == result.score && Objects.equals(desc, result.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, desc);
    }

}
