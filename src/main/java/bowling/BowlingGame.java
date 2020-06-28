package bowling;

import bowling.domain.Player;
import bowling.ui.InputView;

public class BowlingGame {

    public static void main(String[] args) {
        String playerName = InputView.getPlayerName();
        Player player = new Player(playerName);
    }
}
