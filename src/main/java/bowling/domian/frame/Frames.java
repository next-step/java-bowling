package bowling.domian.frame;


import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<FrameResult> frameResults;
    private Frame currentFrame;

    private Frames() {
        this.frameResults = new ArrayList<>();
        this.currentFrame = NormalFrame.firstFrame();
    }

    public static Frames init() {
        return new Frames();
    }

    public boolean isGameEnd() {
        return currentFrame instanceof FinalFrame &&
                currentFrame.isGameEnd();
    }


}
