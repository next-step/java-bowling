package step2.domain;

import step2.strategy.PitchesStrategy;

public class FinalFrame implements Frame{
    private final int frameNo;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
    }

    @Override
    public void pitches(PitchesStrategy strategy) {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public void linkNext(Frame frame) {

    }

    @Override
    public Frame makeNext() {
        return null;
    }
}
