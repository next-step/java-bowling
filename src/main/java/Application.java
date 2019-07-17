import domain.BowlingGame;
import domain.Pins;
import domain.Player;
import domain.frame.FrameIndex;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = Player.from(InputView.askPlayerName());
        BowlingGame bowlingGame = BowlingGame.from(player);
        OutputView.printBoard(bowlingGame);

        while(!bowlingGame.isGameOver()) {
            FrameIndex currentFrameIndex = bowlingGame.currentFrame().getIndex();
            Pins fallenPins = InputView.askFallenPins(currentFrameIndex);

            bowlingGame.play(fallenPins);
            OutputView.printBoard(bowlingGame);
        }
    }
}
