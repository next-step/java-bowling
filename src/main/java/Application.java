import domain.BowlingGame;
import domain.Player;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = Player.from(InputView.askPlayerName());

        BowlingGame bowlingGame = BowlingGame.from(player);
        OutputView.printBoard(bowlingGame);

        int fallenPins = InputView.askFallenPins(1);
    }
}
