package bowling.domain.frame;

import bowling.domain.bowl.Bowls;

public class NormalFrame extends Frame {
    public NormalFrame(FrameNumber frameNumber, Bowls bowls) {
        super(frameNumber, bowls);

        if (!frameNumber.isNormal()) {
            throw new IllegalArgumentException(
                    String.format("frameNumber(%s)는 NormalFrame 범위(%s <= n <= %s)에 있지 않습니다.",
                            frameNumber, FrameNumber.MIN, FrameNumber.MAX_IN_NORMAL_FRAME));
        }
    }

    @Override
    public Frame next() {
        if (frameNumber.isMaxInNormal()) {
            return new FinalFrame(frameNumber.next(), Bowls.initialize());
        }
        return new NormalFrame(frameNumber.next(), Bowls.initialize());
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
