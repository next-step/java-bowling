package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingMain {
    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        Player player = new Player(playerName);
        OutputView.printDashBoard(player);

        for (int i = 0; i < Frames.LAST_FRAME; i++) {
            Frame frame = player.frameByIndex(i);
            playFrame(player, frame);
        }
    }

    private static void playFrame(Player player, Frame frame) {
        while (frame.isAddAble()) {
            int pinCount = InputView.inputPinCount(frame);
            frame.fallDown(pinCount);
            OutputView.printDashBoard(player);
        }
    }
}
