package bowling.app;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingExecutor {

    public static final int INIT_FRAME_NUMBER = 1;
    public static final Frame INIT_FRAME = new Frame(INIT_FRAME_NUMBER);

    private BowlingExecutor() {

    }

    public static void execute(Player player) {
        Frame frame = INIT_FRAME;
        OutputView.printBoard(player, INIT_FRAME);
        while (frame != null) {
            int numberOfPins = InputView.getNumberOfPins();
            frame = frame.bowl(numberOfPins);
            OutputView.printBoard(player, INIT_FRAME);
        }
    }

}
