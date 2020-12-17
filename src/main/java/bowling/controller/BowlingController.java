package bowling.controller;

import bowling.model.User;
import bowling.model.frame.FrameResult;
import bowling.model.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingController {

    public void run() {
        String userName = InputView.printInputUserNameMessage();
        User user = User.from(userName);
        FrameResult frameResult = FrameResult.empty;

        ResultView.printHeadFrame();
        ResultView.printFrame(user, frameResult);

        Frames frames = new Frames();

        while (!frames.isFinished()) {
            int fallenPins = InputView.printInputFallenPinsMessage(frames.nowFrameNumber());
            frames.bowling(fallenPins);
            frameResult = frames.result();
            ResultView.printHeadFrame();
            ResultView.printFrame(user, frameResult);
            ResultView.printFrame(user, frames.getScores());
        }

    }
}
