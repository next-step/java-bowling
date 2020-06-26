package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;

public class BowlingGameStarter {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame(Validator.checkPlayerName(InputView.getPlayerName()));
        bowlingGame.start();
    }
}
