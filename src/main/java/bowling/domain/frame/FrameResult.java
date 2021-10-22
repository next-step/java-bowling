package bowling.domain.frame;

import java.util.Objects;

public class FrameResult {

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
        return new FrameResult(-1, desc);
    }

    public String score() {
        if (score == -1) {
            return "";
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
