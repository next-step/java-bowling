package bowling.client;

import bowling.domain.BowlingGame;
import bowling.view.InputView;

public class GameClient {
    public static void main(String args[]) {
        BowlingGame bowlingGame = new BowlingGame(InputView.inputName());
        bowlingGame.run();
    }
}
