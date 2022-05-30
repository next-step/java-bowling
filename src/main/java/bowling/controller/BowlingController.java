package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.List;

public class BowlingController {
    public void run() {
        List<Player> players = InputView.inputPlayer();
        BowlingGames bowlingGames = BowlingGames.create(players);
        OutputView.printBowlingScoreBoard(bowlingGames.getValues());

        while (bowlingGames.isNextPitching()) {
            bowlingGames.increaseFrameNumber();
            bowlingGames.getValues()
                    .forEach(bowlingGame -> matchByPlayer(bowlingGames, bowlingGame));
        }
    }

    private void matchByPlayer(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        while (bowlingGame.isFrameProgress(bowlingGames.getFrameNumber())) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getPlayerName()));
            OutputView.printBowlingScoreBoard(bowlingGames.getValues());
        }
    }
}
