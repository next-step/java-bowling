package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        Frame frame = NormalFrame.createFirstFrame();

        ResultView.printScoreBoard();
        ResultView.printScoreResultBoard(user, Frames.createFramesByFirstFrame(frame).values());
    }

}
