package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;

public class Main {

    public static void main(String[] args) {
        Player player = InputView.playerNameView();

        BowlingGame bowlingGame = new BowlingGame(player);
    }
}
