package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements PlayableFrame {

    private final Frame frame;

    public FinalFrame(int number) {
        this.frame = new Frame(number);
    }

    @Override
    public void addPintCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        frame.addPintCount(pinCount);
    }

    @Override
    public List<PinCount> pinCounts() {
        return frame.pinCounts();
    }

    @Override
    public FrameNumber number() {
        return frame.number();
    }

    @Override
    public boolean isDone() {

        if (pintCounts.size() == 3) {
            return true;
        }
        if (pintCounts.size() == 2 && totalPinCounts < 10) {
            return true;
        }
        return false;
    }
}
