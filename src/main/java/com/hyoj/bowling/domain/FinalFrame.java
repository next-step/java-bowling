package com.hyoj.bowling.domain;

public class FinalFrame implements Frame {
    private Frame frame;
    private Shot shot;

    public FinalFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public FinalFrame add(int knockDownPinsCount) {
        this.shot = new Shot(knockDownPinsCount);
        return this;
    }

    @Override
    public boolean isDone() {
        return frame.isDone();
    }

    @Override
    public boolean canOneMoreShot() {
        return frame.canOneMoreShot();
    }

    @Override
    public String toString() {
        if (shot != null && shot.isAllDown()) {
            return frame.toString() + "|" + MarkType.STRIKE;
        }

        return frame.toString() + "|" + shot;
    }
}
