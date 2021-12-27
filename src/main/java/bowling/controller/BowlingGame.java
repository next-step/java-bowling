package bowling.controller;

import bowling.domain.factory.FrameFactory;
import bowling.domain.result.BowlingResult;
import bowling.domain.value.Frames;
import bowling.domain.value.Pins;
import bowling.domain.value.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {

    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();
    private static FrameFactory frameFactory = new FrameFactory();

    public BowlingGame(InputView inputView, OutputView outputView, FrameFactory frameFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.frameFactory = frameFactory;
    }

    public void start() {

        String name = InputView.inputUserName();
        User user = new User(name);

        BowlingResult result = BowlingResult.from(Frames.from(frameFactory.create()));
        outputView.printBowlingResult(user, result);
        while(!result.isGameOver()) {
            Pins pins = inputView.getBowlPinCount(result.currentFrameNumber());
            result.bowl(pins);

            outputView.printBowlingResult(user, result);
        }
    }

}
