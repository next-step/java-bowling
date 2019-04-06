import domain.BowlingGame;
import domain.PlayerName;
import view.InputView;
import view.ResultView;

public class ConsoleApplicationLauncher {

    public static void main(String[] args) {
        PlayerName playerName = InputView.getPlayerName();
        BowlingGame game = new BowlingGame(playerName);
        do {
            try {
                game.play(InputView.getBowl(game.getNextFrameNumber()));
                ResultView.showResult(game);
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println();
                continue;
            }
        } while(game.isContinuable());
    }
}