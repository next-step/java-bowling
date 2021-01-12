package bowling.domain.frame;

import bowling.bowlingexception.IllegalFrameRecordException;
import bowling.bowlingexception.IllegalPinRangeException;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame extends Frame {

    private final List<Integer> downedPins;

    public NormalFrame() {
        downedPins = new ArrayList<>();
    }

    @Override
    public void record(int downedPin) {
        if (isEnd()) {
            throw new IllegalFrameRecordException();
        }

        downedPins.add(downedPin);

        if (!hasValidRange()) {
            throw new IllegalPinRangeException();
        }
    }

    @Override
    public boolean isEnd() {
        return (downedPins.size() == 1 && downedPins.get(0) == 10) || downedPins.size() == 2;
    }

    private boolean hasValidRange() {
        int sum = 0;
        for (Integer value : downedPins) {
            sum += value;
        }

        return 0 <= sum && 10 >= sum;
    }
}
