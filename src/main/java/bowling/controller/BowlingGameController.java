package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.domain.frame.FrameFactory;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    private final InputView inputView;

    private final ResultView resultView;

    private final FrameFactory frameFactory;

    public BowlingGameController() {
        this(new InputView(), new ResultView(), new FrameFactory());
    }

    public BowlingGameController(InputView inputView, ResultView resultView, FrameFactory frameFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.frameFactory = frameFactory;
    }

    public void start() {
        Player player = inputView.inputPlayerName();
        BowlingGame bowlingGame = BowlingGame.of(Frames.create(frameFactory.create()), player);

        resultView.printBowlingGameResult(bowlingGame);

        while (bowlingGame.isRunning()) {
            int currentRound = bowlingGame.getCurrentRound();
            Pins pins = inputView.inputPins(currentRound);
            bowlingGame.pitch(pins);
            resultView.printBowlingGameResult(bowlingGame);
        }
    }

}
