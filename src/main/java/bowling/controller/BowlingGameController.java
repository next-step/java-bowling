package bowling.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        try {
            doRun();
        } catch (Exception e) {
            resultView.printError(e.getMessage());
        }
    }

    private void doRun() {
        List<BowlingGame> bowlingGames = initGames(inputView.getPlayerCount());

        resultView.printScoreBoard(bowlingGames);

        while (isGamePlayable(bowlingGames)) {
            bowlingGames.forEach(bowlingGame -> play(bowlingGames, bowlingGame));
        }
    }

    private List<BowlingGame> initGames(int playerCount) {
        return IntStream.range(1, playerCount + 1)
            .mapToObj(inputView::getPlayerName)
            .map(Player::new)
            .map(BowlingGame::new)
            .collect(Collectors.toList());
    }

    private boolean isGamePlayable(List<BowlingGame> bowlingGames) {
        return bowlingGames.stream()
            .anyMatch(BowlingGame::isGamePlayable);
    }

    private void play(List<BowlingGame> bowlingGames, BowlingGame bowlingGame) {
        while(true) {
            int falledPins = inputView.getFalledPins(bowlingGame.getPlayerName());
            boolean isFrameEnded = bowlingGame.bowl(falledPins);
            resultView.printScoreBoard(bowlingGames);

            if (isFrameEnded) {
                break;
            }
        }
    }
}
