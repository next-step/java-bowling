package bowling.controller;

import bowling.domain.factory.FrameFactory;
import bowling.domain.value.BowlingClub;
import bowling.domain.value.BowlingGame;
import bowling.domain.value.Pins;
import bowling.domain.value.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {
    private static final int INIT_PLAYER_NUMBER = 1;

    private final InputView inputView;
    private final ResultView resultView;
    private final FrameFactory frameFactory;

    public BowlingController(InputView inputView, ResultView resultView, FrameFactory frameFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.frameFactory = frameFactory;
    }

    public void start() {
        BowlingClub bowlingClub = initBowlingClub();

        while (!bowlingClub.isGameOver()) {
            playGame(bowlingClub);
        }
    }

    private BowlingClub initBowlingClub() {
        List<Player> players = IntStream.rangeClosed(INIT_PLAYER_NUMBER, inputView.inputPlayerNumber())
                .mapToObj(inputView::inputPlayerName)
                .collect(Collectors.toList());

        return BowlingClub.of(players, frameFactory);
    }

    private void playGame(BowlingClub bowlingClub) {
        bowlingClub.getBowlingGames().forEach(bowlingGame -> playTurn(bowlingClub, bowlingGame));
    }

    private void playTurn(BowlingClub bowlingClub, BowlingGame bowlingGame) {
        int playFrameNumber = bowlingGame.currentFrameNumber();

        while (!bowlingGame.isFrameOver(playFrameNumber)) {
            Pins pins = inputView.inputPins(bowlingGame.getPlayer());

            bowlingGame.pitch(pins);

            resultView.printBowlingResult(bowlingClub);
        }
    }
}
