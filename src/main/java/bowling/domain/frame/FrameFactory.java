package bowling.domain.frame;

public class FrameFactory {

    private FrameFactory() {
    }

    public static Frame firstFrame() {
        return NormalFrame.first();
    }
}
