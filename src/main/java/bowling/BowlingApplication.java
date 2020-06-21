package bowling;

import bowling.domain.player.Player;
import bowling.view.InputView;

public class BowlingApplication {

    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayer());
    }
}
