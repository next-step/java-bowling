package bowling.app;

import bowling.domain.NormalFrame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingExecutor {

    public static final int INIT_FRAME_NUMBER = 1;
    public static final NormalFrame INIT_NORMAL_FRAME = new NormalFrame(INIT_FRAME_NUMBER);

    private BowlingExecutor() {

    }

    public static void execute(Player player) {
        NormalFrame normalFrame = INIT_NORMAL_FRAME;
        OutputView.printBoard(player, INIT_NORMAL_FRAME);
        while (normalFrame != null) {
            int numberOfPins = InputView.getNumberOfPins();
            normalFrame = normalFrame.bowl(numberOfPins);
            OutputView.printBoard(player, INIT_NORMAL_FRAME);
        }
    }

}
