package bowling;

import bowling.domain.Player;
import bowling.tobe.Frames;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGame {
    public static final int FIRST_FRAME = 1;
    public static final int FINAL_FRAME = 10;

    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayerName(), FINAL_FRAME);
        Frames frames = new Frames(player);

        ResultView.printBoard(frames);

        while (true) {
            boolean isFinish = frames.bowling(InputView.getPin(frames.getCurrentFrameNo()));
            ResultView.printBoard(frames);

            if (isFinish) {
                break;
            }
        }
    }
}
