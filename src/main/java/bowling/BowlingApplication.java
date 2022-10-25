package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.FrameHistory;
import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingApplication {
    public static void main(String[] args) {
//        Player player = new Player("osc");
        Player player = InputView.inputPlayer();

        BowlingGame game = new BowlingGame(player);

        game.doGame();
    }
}
