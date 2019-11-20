package bowling;

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
            playFrame(player, i);
        }
    }

    private static void playFrame(Player player, int index) {
        while (player.isFallDownAble(index)) {
            int pinCount = InputView.inputPinCount(player.frameByIndex(index));
            player.fallDown(index, pinCount);
            OutputView.printDashBoard(player);
        }
    }
}
