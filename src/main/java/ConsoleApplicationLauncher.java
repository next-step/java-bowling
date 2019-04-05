import domain.BowlingGame;
import domain.PlayerName;
import view.InputView;
import view.ResultView;

public class ConsoleApplicationLauncher {

    public static void main(String[] args) {
        PlayerName playerName = InputView.getPlayerName();

        BowlingGame game = new BowlingGame(playerName);
        //game.prepareInitialFrame();
        do {
            game.play(InputView.getBowl(game.getRecentFrameNumber()));
            ResultView.showResult(game);
        } while(game.isContinuable());
    }
}