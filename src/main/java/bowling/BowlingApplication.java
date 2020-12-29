package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.FrameSet;
import bowling.view.ConsoleView;
import bowling.view.UserInput;

import java.util.Random;

public class BowlingApplication {

    public static void main(String[] args) {
        UserInput input = new UserInput();
        ConsoleView view = new ConsoleView();

        String playName = input.readPlayerName();
        BowlingGame game = new BowlingGame(playName);

        game.start( (bowlingGame) -> {
            view.printScoreSheets(bowlingGame.getReaders());

            FrameSet current;
            while (!bowlingGame.isEnd()) {
                current = bowlingGame.nextFrameSet();
                current.mark(
                        frame -> input.readCountOfFallDownPins(frame.getFrameNo()),
                        frame -> view.printScoreSheets(bowlingGame.getReaders())
                );
            }
        });

    }
}
