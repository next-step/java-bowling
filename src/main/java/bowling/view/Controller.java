package bowling.view;

import bowling.BowlingGames;

public class Controller {

    private Controller() {}

    public static void play() {
        int numberOfPlayer = InputView.inputNumberOfPlayer();
        BowlingGames bowlingGames = new BowlingGames();
        for (int i = 0; i < numberOfPlayer; i++) {
            bowlingGames.enter(InputView.inputPlayerName());
        }
        OutputView.printCurrentStatus(bowlingGames);

        while (bowlingGames.hasNextGame()) {
            bowlingGames.bowl(InputView.inputFallenPins(bowlingGames.getTurnPlayerName()));
            OutputView.printCurrentStatus(bowlingGames);
        }
    }
}
