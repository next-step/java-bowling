package bowling;

import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayerName());
        ResultView.printInitBowlingBoard(player);

        while(!player.isDone()) {
            player.bowl(InputView.inputBowlingPin(player.currentFrameNumber()));
            ResultView.printBowlingBoard(player);
        }
    }
}
