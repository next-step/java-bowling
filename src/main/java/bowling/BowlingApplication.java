package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.FrameSet;

import java.util.Random;

public class BowlingApplication {

    public static void main(String[] args) {
        BowlingGame game = new BowlingGame("NIO");

        game.start();

        FrameSet current;
        while (game.hasNextFrameSet()) {
            current = game.nextFrameSet();
            current.playForEachFrame(frame -> {
                while (!frame.isEnd()) {
                    int countOfFallDown = getUserInput();
                    // display scoreboard
                    frame.inputPins(countOfFallDown);
                }
            });
        }

    }

    private static int getUserInput() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
