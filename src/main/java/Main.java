import bowling.domain.DownedPinCount;
import bowling.domain.game.Game;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
	public static void main(String[] args) {
		Game game = new Game(InputView.getPlayer());
		OutputView.showInitializedGame(game);
		while (!game.isGameFinished()) {
			DownedPinCount currentFramePitch = InputView.getCurrentFramePitch(game.getCurrentFrameSequence());
			game.play(currentFramePitch);
			OutputView.showDashBoard(game);
		}
	}
}
