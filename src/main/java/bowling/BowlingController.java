package bowling;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingController {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();

        Player player = Player.from(name);
    }

}
