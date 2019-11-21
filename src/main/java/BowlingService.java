import board.Name;
import frame.Frame;
import frame.Frames;
import frame.LastFrame;
import view.InputView;
import view.OutputView;

public class BowlingService {

    public static void rollUntilLast(Name name, Frames frames) {
        Frame nowFrame = frames.getNowFrame();
        if (frames.reachLast()) {
            return;
        }
        nowFrame.bowling(InputView.inputScore(frames.getNowFrameNumber()));
        showBoard(name, frames);
    }

    public static void rollLast(Name name, Frames frames) {
        LastFrame lastFrame = frames.getLastFrame();
        lastFrame.bowling(InputView.inputScore(frames.getNowFrameNumber()));
        showBoard(name, frames);
    }

    private static void showBoard(Name name, Frames frames) {
        //show
        OutputView.showBasic();
        OutputView.showName(name.getName());
        OutputView.showFrame(frames);
        OutputView.showLastFrame(frames.getLastFrame());
    }
}
