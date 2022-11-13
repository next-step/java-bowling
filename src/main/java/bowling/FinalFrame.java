package bowling;

import java.util.List;

public class FinalFrame implements Frame {

    public static final int FINAL_FRAME_NUMBER = 10;

    @Override
    public Frame bowl(Pin falledPins) {
        return null;
    }

    @Override
    public Frame nextFrame(Pin falledPins) {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Pin> getScores() {
        return null;
    }
}
