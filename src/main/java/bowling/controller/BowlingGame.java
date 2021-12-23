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

    private final User user;
    private Frame frame;
    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();

    public BowlingGame(User name) {
        this.user = name;
        this.frame = new NormalFrame(FIRST_FRAME_NO);

    }

    public void start() {

        Results results = new Results();
        printBowlingResult(results);

        while(isNotFinished()) {
            results.set(bowl());
            printBowlingResult(results);
        }
        if(bonusGame()) {
            results.set(bowl());
            printBowlingResult(results);
        }
    }

    private void printBowlingResult(Results results) {
        outputView.printBowlingResult(this.user, results);
    }

    private boolean isNotFinished() {
        return this.frame.isNormalFrame() || !this.frame.isFinished();
    }

    private Result bowl() {
        int knockedDownPin = inputView.getBowlPinCount(this.frame.getFrameNo());
        Frame nextFrame = frame.bowl(knockedDownPin);

        Result gameResult = getGameResult();
        this.frame = nextFrame;
        return gameResult;
    }

    private Result getGameResult() {
        return new Result(this.frame.getFrameNo(), this.frame.getStateMark());
    }

    public boolean bonusGame() {
        return this.frame.isStrike() || this.frame.isSpare();
    }
}
