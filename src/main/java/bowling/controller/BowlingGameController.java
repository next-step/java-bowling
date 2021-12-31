package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.OutputView;

public final class BowlingGameController {

    private BowlingGameController() {
    }

    public static void play() {
        BowlingGames bowlingGames = BowlingGames.create(InputView.inputPlayersInformation());
        OutputView.printCurrentStatus(bowlingGames);
        while (bowlingGames.hasNextPitching()) {
            bowlingGames.increaseFrameIndex();
            bowlingGames.getBowlingGames()
                    .forEach(bowlingGame -> eachPlay(bowlingGames, bowlingGame));
        }
    }

    private static void eachPlay(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        while (bowlingGame.hasFrameInProgress(bowlingGames.getCurrentFrameIndex())) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getPlayerName()));
            OutputView.printCurrentStatus(bowlingGames);
        }
    }
}
