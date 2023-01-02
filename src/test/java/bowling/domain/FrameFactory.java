package bowling.domain;

public class FrameFactory {

    public static Frame frameImplProvider(int frameNumber) {
        if (frameNumber == 10) {
            return FinalFrame.init();
        }
        return NormalFrame.init(frameNumber);
    }

}
