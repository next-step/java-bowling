import bowling.BowlingGame;
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

        while (!bowlingGame.isOver()) {
            int pinCount = InputView.inputDropPinCount(bowlingGame.getFrameCount());
            bowlingGame.bowl(Player.of("AAA"), Pin.of(pinCount));

            OutputView.printBowlingGame(BowlingGameResult.newInstance(bowlingGame));
        }
    }
}
