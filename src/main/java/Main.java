import bowling.BowlingGame;
import bowling.Player;
import bowling.view.InputView;

public class Main {
    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayerName());
        BowlingGame bowlingGame = BowlingGame.newInstance(player);
    }
}
