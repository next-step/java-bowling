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
        Players players = createPlayers(numberOfPlayer);
        playGame(players);
        inputView.close();
    }

    private Players createPlayers(NumberOfPlayer numberOfPlayer) {
        return new Players(IntStream.range(0, numberOfPlayer.numberOfPlayer())
                .mapToObj(i -> new BowlingGame(inputView.playerName(i)))
                .collect(Collectors.toList()));
    }

    protected void playGame(Players players) {
        resultView.printResult(players.names(), players.frames(), players.scores());

        while (!players.isAllFinished()) {
            players.players()
                    .forEach(player -> eachFramePlay(player, players));
        }
    }

    private void eachFramePlay(BowlingGame bowlingGame, Players players) {
        int currentFrameIndex = bowlingGame.currentFrameIndex();

        while (!bowlingGame.hasFinishedFrame(currentFrameIndex)) {
            bowlingGame.throwBowl(inputView.pinCount(bowlingGame.name()));
            resultView.printResult(players.names(), players.frames(), players.scores());
        }
    }
}
