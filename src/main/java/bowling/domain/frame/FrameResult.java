package bowling.domain.frame;

import java.util.Objects;

public class FrameResult {

    private static final int CANNOT_CALCULATED_SCORE_VALUE = -1;
    private static final String BLANK_SCORE_STRING = "";

    private final int score;
    private final String desc;

    private FrameResult(int score, String desc) {
        this.score = score;
        this.desc = desc;
    }

    public static FrameResult of(int score, String desc) {
        return new FrameResult(score, desc);
    }

    public static FrameResult createFrameResultByNoCaculatedScore(String desc) {
        return new FrameResult(CANNOT_CALCULATED_SCORE_VALUE, desc);
    }

    public String score() {
        if (score == CANNOT_CALCULATED_SCORE_VALUE) {
            return BLANK_SCORE_STRING;
        }
        return String.valueOf(score);
    }

    public String desc() {
        return desc;
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
