package bowling.domain.engine.frame;

import bowling.domain.concrete.frame.FinalFrame;
import bowling.domain.concrete.frame.NormalFrame;

public class FrameCreator {

    private static final int MAX_FRAMES = 10;

    private int count = 1;

    public Frame create() {
        if (count > MAX_FRAMES) {
            throw new IllegalStateException("더 이상 프레임을 생성할 수 없습니다.");
        }

        if (count++ == MAX_FRAMES) {
            return FinalFrame.init();
        }
        return NormalFrame.init();
    }

}
