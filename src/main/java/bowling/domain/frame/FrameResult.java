package bowling.domain.frame;

import bowling.domain.score.Score;

public final class FrameResult {

    private final Frame frame;
    private final Score score;

    private FrameResult(final Frame frame, final Score score) {
        this.frame = frame;
        this.score = score;
    }

    public static FrameResult of(final Frame frame, final Score score) {
        return new FrameResult(frame, score);
    }
}
