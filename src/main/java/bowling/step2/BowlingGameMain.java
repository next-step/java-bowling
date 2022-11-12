package bowling.step2;

import bowling.step2.domain.BowlingGame;
import bowling.step2.view.InputView;

public class BowlingGameMain {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.startGame(name);
    }
}
