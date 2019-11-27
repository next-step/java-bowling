import frame.Frame;
import frame.Frames;
import frame.LastFrame;
import view.InputView;

public class BowlingService {

    public static void rollUntilLast(Frames frames) {
        Frame nowFrame = frames.getNowFrame();
        if (frames.reachLast()) {
            return;
        }
        int score = InputView.inputScore(frames.getNowFrameNumber());
        nowFrame.bowling(score);
    }

    public static void rollLast(Frames frames) {
        LastFrame lastFrame = frames.getLastFrame();
        int score = InputView.inputScore(frames.getNowFrameNumber());
        lastFrame.bowling(score);
    }

}
