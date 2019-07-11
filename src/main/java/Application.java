import domain.PlayerName;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        PlayerName playerName = PlayerName.from(InputView.askPlayerName());
        OutputView.printScoreBoard(playerName);
    }
}
