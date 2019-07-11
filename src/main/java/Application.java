import domain.PlayerName;
import domain.ScoreBoard;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        PlayerName playerName = PlayerName.from(InputView.askPlayerName());
        OutputView.printInitialBoard(playerName);

        ScoreBoard scoreBoard = ScoreBoard.of(playerName);
//        OutputView.printScoreBoard(scoreBoard);
    }
}
