package bowling.controller;

import bowling.domain.factory.FrameFactory;
import bowling.domain.value.BowlingClub;
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

        BowlingClub bowlingClub = BowlingClub.from(frameFactory.create());

        while (!bowlingClub.isGameOver()) {
            Pins pins = inputView.inputPins(bowlingClub.getCurrentFrameNumber());

            bowlingClub.pitch(pins);

            resultView.printBowlingResult(bowlingClub, player);
        }
    }
}
