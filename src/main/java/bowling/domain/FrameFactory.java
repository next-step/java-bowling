package bowling.domain;

public class FrameFactory {

    public static Frame createFirstFrame(){
        return new NormalFrame(1);
    }

    public static Frame createNextFrame(int frameNo) {
        if (isNextFrameFinal(frameNo)) {
            return new FinalFrame(frameNo + 1);
        }
        return new NormalFrame(frameNo + 1);
    }

    private static boolean isNextFrameFinal(int currentFrameNo) {
        return currentFrameNo == 9;
    }
}
