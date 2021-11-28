package bowling.controller;

import bowling.domain.factory.FrameFactory;
import bowling.domain.frame.Frame;
import bowling.domain.value.Pins;
import bowling.domain.value.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingController {
    private final InputView inputView;
    private final ResultView resultView;
    private final FrameFactory frameFactory;

    public BowlingController(InputView inputView, ResultView resultView, FrameFactory frameFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.frameFactory = frameFactory;
    }

    public void play() {
        Player player = inputView.inputPlayerName();

        Frame frame = frameFactory.create();

        while (!frame.isGameOver()) {
            Pins pins = inputView.inputPins(frame.getCurrentFrameNumber());

            frame.knockedDown(pins);

            resultView.printBowlingResult(frame, player);
        }
    }
}
