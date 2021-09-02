package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApp {
    public static void main(String[] args) {
        Player player = new Player(InputView.requirePlayerName());
        ResultView.printResultColumn();

        BowlingGame bowlingGame = new BowlingGame(player.getName());
    }
}
