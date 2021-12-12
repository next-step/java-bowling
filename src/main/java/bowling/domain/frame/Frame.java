package bowling.domain.frame;

import bowling.domain.result.FrameResult;

public interface Frame {
    Frame bowl(Pin pin);

    boolean isGameEnd();

    boolean isFinished();

    boolean isEqualsRound(Frame frame);

    Round round();

    FrameResult createResult();

    boolean hasNext();

    Frame next();
}
