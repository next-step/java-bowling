import domain.BowlingGame;
import domain.Pins;
import domain.frame.Frame;
import io.InputView;
import io.OutputResult;

import java.util.List;

public class BowlingGameApplication {

    public static void main(String[] args) {

        String name = InputView.askPlayerInitial();
        BowlingGame bowlingGame = new BowlingGame();

        while (bowlingGame.isRunning()) {
            Integer currentIndex = bowlingGame.getCurrentFrameIndex().value();
//            Pins pins = InputView.askKnockDownedPins(currentIndex);
//            List<Frame> frames = bowlingGame.bowl(pins);
//            OutputResult.printBoard(name, frames);
        }
    }
}
