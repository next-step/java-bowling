package bowling.domain;

public class FrameFactory {

    public static Frame frameImplProvider(int frameNumber) {
        if (frameNumber == 10) {
            return new FinalFrame();
        }
        return NormalFrame.init(frameNumber);
    }

}
