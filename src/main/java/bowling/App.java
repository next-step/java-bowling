package bowling;

import bowling.exception.CannotCreateException;
import bowling.game.BowlingGame;

public class App {
    public static void main(String[] args) throws CannotCreateException {
        BowlingGame game = new BowlingGame();

        game.start();

        game.play();
    }
}
