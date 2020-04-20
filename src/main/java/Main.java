import bowling.game.BowlingGame;
import bowling.Pin;
import bowling.Player;
import bowling.dto.BowlingGameResult;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayerName());

        BowlingGame bowlingGame = BowlingGame.newInstance(player);

        OutputView.printBowlingGame(BowlingGameResult.newInstance(bowlingGame));

        while (!bowlingGame.isAllFramesOver()) {
            int pinCount = InputView.inputDropPinCount(bowlingGame.getFrameCount());
            bowlingGame.bowl(Pin.of(pinCount));

            OutputView.printBowlingGame(BowlingGameResult.newInstance(bowlingGame));
        }
    }
}
