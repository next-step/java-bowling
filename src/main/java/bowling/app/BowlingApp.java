package bowling.app;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingApp {

    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayerName());
        BowlingExecutor.execute(player);
    }

}
