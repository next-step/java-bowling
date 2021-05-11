package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.FrameNullPointerException;
import bowling.exception.ScoreNullPointerException;

import java.util.Objects;

public final class FrameResult {

    private final Frame frame;
    private final Score score;

    public static FrameResult of(final Frame frame, final Score score) {
        return new FrameResult(frame, score);
    }

    private FrameResult(final Frame frame, final Score score) {
        validateNull(frame, score);
        this.frame = frame;
        this.score = score;
    }

    private final void validateNull(final Frame frame, final Score score) {
        if (Objects.isNull(frame)) {
            throw new FrameNullPointerException();
        }
        if (Objects.isNull(score)) {
            throw new ScoreNullPointerException();
        }
    }


}
