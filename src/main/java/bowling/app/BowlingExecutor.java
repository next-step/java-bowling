package bowling.app;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import static bowling.domain.frame.AbstractFrame.FIRST_FRAME_NUMBER;

public class BowlingExecutor {

    public static final Frame FIRST_FRAME = new NormalFrame(FIRST_FRAME_NUMBER);

    private BowlingExecutor() {

    }

    public static void execute(Player player) {
        Frame frame = FIRST_FRAME;
        OutputView.printBoard(player, FIRST_FRAME);
        while (frame != null) {
            int numberOfPins = InputView.getNumberOfPins();
            frame = frame.bowl(numberOfPins);
            OutputView.printBoard(player, FIRST_FRAME);
        }
    }

}
