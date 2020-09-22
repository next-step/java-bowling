package bowling.domain;

public class FrameFactory {

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public static Frame next(Frame frame) {
        return new NormalFrame(); // TODO 로직 작성
    }

    public static FinalFrame last() {
        return new FinalFrame();
    }
}
