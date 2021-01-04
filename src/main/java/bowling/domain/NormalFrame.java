package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {

    private final int frameNo;
    private final Frame prev;
    private Frame next;
    private PinMarks pinMarks;

    NormalFrame(int frameNo, Frame prev) {
        this.prev = prev;
        this.frameNo = frameNo;
        this.pinMarks = new NormalFramePinMarks();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDownPins) {
        pinMarks.mark(countOfFallDownPins);
    }

    @Override
    public long getCountOfMarks() {
        return pinMarks.getCountOfMarks();
    }

    @Override
    public Status getStatus() {
        if (pinMarks.isStrike()) return Status.Strike;
        if (pinMarks.getCountOfAllFallDownPins() == 0) return Status.Gutter;
        if (pinMarks.getCountOfAllFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        return Status.Miss;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isOpen() {
        Status status = getStatus();
        return  status == Status.Gutter
                || status == Status.Miss;
    }

    @Override
    public boolean isEnd() {
        return pinMarks.isAllMarked();
    }

    @Override
    public Frame createNext() {
        if (isNextFrameFinal()) {
            next = Frame.createFinal(frameNo + 1, this);
            return next;
        }

        next = Frame.createNormal(frameNo + 1, this);
        return next;
    }

    @Override
    public Frame next() {
        return next;
    }

    private boolean isNextFrameFinal() {
        return frameNo == 9;
    }

    @Override
    public FrameScore getScore() {
        return FrameScoreCalculatorFactory.create(isFinal(), getStatus())
                .calculate(this);
    }

    @Override
    public FrameInfo toFrameInfo() {
        return FrameInfo.of(frameNo, pinMarks.toSigns(), getScore());
    }

    @Override
    public List<Integer> getCountListOfFallDownPins() {
        return pinMarks.toList()
                .stream()
                .map(pinMark -> pinMark.getCountOfFallDownPins())
                .collect(Collectors.toList());
    }
}
