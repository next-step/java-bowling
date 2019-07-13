import domain.Player;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = Player.from(InputView.askPlayerName());
        OutputView.printInitialBoard(player);
    }
}
