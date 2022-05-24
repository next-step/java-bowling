package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.exception.InvalidPitchException;
import bowling.domain.frame.FrameFactory;
import bowling.exception.InvalidPinsException;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

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
        BowlingGames bowlingGames = initBowlingGames();
        resultView.printBowlingGameResult(bowlingGames);

        while (bowlingGames.isRunning()) {
            pitch(bowlingGames);
            resultView.printBowlingGameResult(bowlingGames);
        }
    }

    private BowlingGames initBowlingGames() {
        List<BowlingGame> bowlingGames = new ArrayList<>();
        int playerCount = inputView.inputPlayerCount();
        List<Player> players = inputView.inputPlayers(playerCount);

        for (Player player : players) {
            Frames frames = Frames.create(frameFactory.create());
            bowlingGames.add(BowlingGame.of(frames, player));
        }

        return BowlingGames.create(bowlingGames);
    }

    private void pitch(BowlingGames bowlingGames) {
        try {
            Pins pins = inputView.inputPins(bowlingGames);
            bowlingGames.play(pins);
        } catch (InvalidPinsException | InvalidPitchException exception) {
            resultView.printExceptionMessage(exception);
        }
    }

}
