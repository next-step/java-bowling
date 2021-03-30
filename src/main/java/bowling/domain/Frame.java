package bowling.domain;

public interface Frame {
    static NormalFrame first() {
        return new NormalFrame(1);
    }

    static FinalFrame last(int finalFrameNumber) {
        return new FinalFrame(finalFrameNumber);
    }

    int number();

}
