package bowling.view;

import bowling.BowlingGame;

public class Controller {

    private Controller() {}

    public static void play() {
        BowlingGame bowlingGame = new BowlingGame(InputView.inputPlayerName());
        OutputView.printCurrentStatus(bowlingGame);

        while (bowlingGame.hasNextBowl()) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.recentFrameNo()));
            OutputView.printCurrentStatus(bowlingGame);
        }
    }
}
