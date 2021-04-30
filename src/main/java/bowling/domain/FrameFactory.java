package bowling.domain;

public class FrameFactory {
    public static FrameType typeFactory(int frameNo, int maxFrameNo) {
        if (isFinalFrame(frameNo, maxFrameNo)) {
            return new FinalFrameType();
        }
        return new NormalFrameType();
    }

    private static boolean isFinalFrame(int frameNo, int maxFrameNo) {
        return frameNo == maxFrameNo;
    }
}
