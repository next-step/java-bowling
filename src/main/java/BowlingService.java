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
        nowFrame.bowling(InputView.inputScore(frames.getNowFrameNumber()));
    }

    public static void rollLast(Frames frames) {
        LastFrame lastFrame = frames.getLastFrame();
        lastFrame.bowling(InputView.inputScore(frames.getNowFrameNumber()));
    }


}
