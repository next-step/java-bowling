package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

abstract public class BaseFrame implements Frame {

    protected int frameNo;
    protected PinMarks pinMarks;
    protected Frame next;

    BaseFrame(int frameNo, PinMarks pinMarks){
        this.frameNo = frameNo;
        this.pinMarks = pinMarks;
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
    public Frame next() {
        return next;
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

    @Override
    public FrameScore getScore() {
        return FrameScoreCalculatorFactory.create(isFinal(), getStatus()).calculate(this);
    }
}
