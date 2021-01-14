package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

abstract public class BaseFrame implements Frame {

    protected final int frameNo;
    protected final PinMarker pinMarker;
    protected Frame next;

    BaseFrame(int frameNo, PinMarker pinMarker){
        this.frameNo = frameNo;
        this.pinMarker = pinMarker;
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDownPins) {
        pinMarker.mark(PinMark.pin(countOfFallDownPins));
    }

    @Override
    public long getCountOfMarks() {
        return pinMarker.getCountOfMarks();
    }

    @Override
    public boolean isEnd() {
        return pinMarker.isCompleted();
    }

    @Override
    public Frame next() {
        return next;
    }

    @Override
    public FrameInfo toFrameInfo() {
        return FrameInfo.of(getFrameNo(), pinMarker.toSigns(), getScore());
    }

    @Override
    public List<Integer> getCountListOfFallDownPins() {
        return pinMarker.toList()
                .stream()
                .map(pinMark -> pinMark.getCountOfFallDownPins())
                .collect(Collectors.toList());
    }

    @Override
    public FrameScore getScore() {
        FrameScoreCalculator calculator = FrameScoreCalculatorFactory.create(isFinal(), getStatus());
        return calculator.calculate(this);
    }
}
