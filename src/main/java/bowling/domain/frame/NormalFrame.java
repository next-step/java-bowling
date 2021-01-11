package bowling.domain.frame;

import bowling.bowlingexception.IllegalFrameRecordException;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private final List<Integer> downedPins;

    public NormalFrame() {
        downedPins = new ArrayList<>();
    }

    public void record(int downedPin) {
        if (isEnd()) {
            throw new IllegalFrameRecordException();
        }
        downedPins.add(downedPin);
    }

    public boolean isEnd() {
        return (downedPins.size() == 1 && downedPins.get(0) == 10) || downedPins.size() == 2;
    }
}
