package bowling.domain;

public class NormalFrame extends Frame {
    public NormalFrame() {
        super();
        this.availability = 2;
    }

    @Override
    public Frame createFrame(int frameNumber) {
        if (frameNumber == 10) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    @Override
    protected void updateCondition(int inputScore) {
        if (inputScore == 10) {
            availability = 0;
        }
    }
}
