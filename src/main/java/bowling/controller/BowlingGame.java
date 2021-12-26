package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.result.Result;
import bowling.domain.result.Results;
import bowling.domain.value.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {

    private static final int FIRST_FRAME_NO = 1;

    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();

    public void start() {

        String name = InputView.inputUserName();
        User user = new User(name);

        Frame frame = new NormalFrame(FIRST_FRAME_NO);

        Results results = new Results();
        printBowlingResult(user, results);

        while(isNotFinished(frame)) {

            int knockedDownPin = inputView.getBowlPinCount(frame.getFrameNo());
            Frame nextFrame = frame.bowl(knockedDownPin);

            results.saveGameResult(getGameResult(frame));
            printBowlingResult(user, results);

            frame = nextFrame;
        }

        if(bonusGame(frame)) {
            results.saveGameResult(getGameResult(frame));
            printBowlingResult(user, results);
        }
    }

    private void printBowlingResult(User user, Results results) {
        outputView.printBowlingResult(user, results);
    }

    private boolean isNotFinished(Frame frame) {
        return frame.isNormalFrame() || !frame.isFinished();
    }

    private Result getGameResult(Frame frame) {
        return new Result(frame.getFrameNo(), frame.getStateMark());
    }

    public boolean bonusGame(Frame frame) {
        return frame.isStrike() || frame.isSpare();
    }
}
