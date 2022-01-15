package bowling.view;

import bowling.BowlingGames;

public class Controller {

    private Controller() {}

    public static void play() {
        BowlingGames bowlingGames = enterPlayer();
        OutputView.printCurrentStatus(bowlingGames);

        while (bowlingGames.hasNextGame()) {
            bowlingGames.bowl(InputView.inputFallenPins(bowlingGames.getTurnPlayerName()));
            OutputView.printCurrentStatus(bowlingGames);
        }
    }

    private static BowlingGames enterPlayer() {
        BowlingGames bowlingGames = new BowlingGames(InputView.inputNumberOfPlayer());
        for (int i = 0; i < bowlingGames.getNumberOfPlayer(); i++) {
            bowlingGames.enter(InputView.inputPlayerName());
        }
        return bowlingGames;
    }
}
