package bowling.app;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingExecutor {

    private BowlingExecutor() {

    }

    public static void execute(Player player) {
        Frame firstFrame = NormalFrame.firstFrame();
        Frame frame = firstFrame;
        OutputView.printBoard(player, firstFrame);
        while (!frame.isEnd()) {
            int numberOfPins = InputView.getNumberOfPins();
            frame = frame.bowl(numberOfPins);
            OutputView.printBoard(player, firstFrame);
        }
    }

}
