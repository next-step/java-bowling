import domain.BowlingGame;
import domain.Pins;
import domain.frame.FrameIndex;
import domain.frame.Frames;
import io.InputView;
import io.OutputResult;

public class BowlingGameApplication {

    public static void main(String[] args) {

        String name = InputView.askPlayerInitial();
        BowlingGame bowlingGame = new BowlingGame();
        FrameIndex currentIndex = FrameIndex.initiate();
        while (bowlingGame.isRunning()) {
            Pins pins = InputView.askKnockDownedPins(currentIndex);
            Frames frames = bowlingGame.bowl(pins);
            currentIndex = frames.getCurrentFrameIndex();
            OutputResult.printBoard(name, frames);
        }
        System.out.println();
    }
}
