package bowling;

import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayer());

        ResultView.printHeader(player);

    }
}
