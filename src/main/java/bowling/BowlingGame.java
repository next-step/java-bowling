package bowling;

import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGame {
    public static final int FIRST_FRAME = 1;
    public static final int FINAL_FRAME = 10;

    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayerName(), FINAL_FRAME);

        ResultView.printBoard(player);
        for (int frame = FIRST_FRAME; frame <= FINAL_FRAME; frame++) {
            playFrame(frame, player);
        }
    }

    private static void playFrame(int frame, Player player) {
        boolean isFrameFinish = player.bowling(InputView.getPin(frame));
        ResultView.printBoard(player);
        if (isFrameFinish) {
            return;
        }
        playFrame(frame, player);
    }
}
