package bowling.domain;

public class FrameFactory {
    public static FrameType factory(int frameNo, int maxFrameNo) {
        if (frameNo == maxFrameNo) {
            return new FinalFrameType();
        }
        return new NormalFrameType();
    }
}
