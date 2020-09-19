package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingFrame {

    public static final int TOTAL_FRAME = 10;
    public static final int FRAME_COUNT = TOTAL_FRAME - 1;

    private static List<NormalFrame> normalFrames = new ArrayList<>();
    private FinalFrame finalFrame;
    private int currentFrame = 0;

    private BowlingFrame(List<NormalFrame> normalFrames, FinalFrame finalFrame) {
        this.normalFrames = normalFrames;
        this.finalFrame = finalFrame;
    }

    public static BowlingFrame init() {
        for (int i = 0; i < FRAME_COUNT; i++) {
            normalFrames.add(NormalFrame.init());
        }
        FinalFrame finalFrame = FinalFrame.init();
        return new BowlingFrame(normalFrames, finalFrame);
    }

    public static List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }

    public int hit(int hit) {
        System.out.println("curent==" + currentFrame);
        System.out.println(FRAME_COUNT);
        if (currentFrame == FRAME_COUNT) {
            return currentFrame = finalFrame.hit(hit, currentFrame);
        }
        return currentFrame = normalFrames.get(currentFrame).hit(hit, currentFrame);
    }
}
