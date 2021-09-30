package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        Frame frame = NormalFrame.createFirstFrame();

        ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame).values());
        for (int i=0; i<9; i++){
            Frame nowFrame = frame.lastFrame();
            Pin first = Pin.of(InputView.inputFrameShot(nowFrame.round()));
            nowFrame.updateScorePin(first);
            ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame).values());
            if (nowFrame.isScoreNextStorable()) {
                Pin second = Pin.of(InputView.inputFrameShot(nowFrame.round()));
                nowFrame.updateScorePin(second);
                ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame).values());
            }
//            ResultView.printBoard(user, Frames.createFramesByFirstFrame());
            nowFrame.createNextFrame();
        }
    }

}
