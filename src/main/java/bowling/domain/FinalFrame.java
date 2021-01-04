package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private final int frameNo;
    private final Frame prev;
    private PinMarks pinMarks;

    FinalFrame(int frameNo, Frame prev) {
        this.prev = prev;
        this.frameNo = frameNo;
        this.pinMarks = new FinalFramePinMarks();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDownPins) {
        pinMarks.mark(PinMark.pin(countOfFallDownPins));
    }

    @Override
    public long getCountOfMarks() {
        return pinMarks.getCountOfMarks();
    }

    @Override
    public Status getStatus() {
        if (pinMarks.isStrike()) return Status.Strike;
        if (pinMarks.getCountOfMarks() == 2 && pinMarks.getCountOfAllFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        if (pinMarks.getCountOfAllFallDownPins() == 0) return Status.Gutter;
        return Status.Miss;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return pinMarks.isAllMarked();
    }

    @Override
    public Frame createNext() {
        return null;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public FrameScore getScore() {

        return FrameScoreCalculatorFactory.create(isFinal(), getStatus()).calculate(this);
    }

    @Override
    public FrameInfo toFrameInfo() {
        return FrameInfo.of(getFrameNo(), pinMarks.toSigns(), getScore());
    }

    @Override
    public List<Integer> getCountListOfFallDownPins() {
        return pinMarks.toList()
                .stream()
                .map(pinMark -> pinMark.getCountOfFallDownPins())
                .collect(Collectors.toList());
    }
}
