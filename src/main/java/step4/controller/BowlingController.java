package step4.controller;

import step4.domain.*;
import step4.view.InputView;
import step4.view.ResultView;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        NumberOfPlayer numberOfPlayer = new NumberOfPlayer(inputView.numberOfPlayer());
        BowlingGames bowlingGames = createPlayers(numberOfPlayer);
        playGame(bowlingGames);
        inputView.close();
    }

    private BowlingGames createPlayers(NumberOfPlayer numberOfPlayer) {
        return new BowlingGames(IntStream.range(0, numberOfPlayer.numberOfPlayer())
                .mapToObj(i -> new BowlingGame(inputView.playerName(i)))
                .collect(Collectors.toList()));
    }

    protected void playGame(BowlingGames bowlingGames) {
        resultView.printResult(bowlingGames.names(), bowlingGames.frames(), bowlingGames.scores());

        while (!bowlingGames.isAllFinished()) {
            bowlingGames.bowlingGames()
                    .forEach(player -> eachFramePlay(player, bowlingGames));
        }
    }

    private void eachFramePlay(BowlingGame bowlingGame, BowlingGames bowlingGames) {
        int currentFrameIndex = bowlingGame.currentFrameIndex();

        while (!bowlingGame.hasFinishedFrame(currentFrameIndex)) {
            bowlingGame.throwBowl(inputView.pinCount(bowlingGame.name()));
            resultView.printResult(bowlingGames.names(), bowlingGames.frames(), bowlingGames.scores());
        }
    }
}
