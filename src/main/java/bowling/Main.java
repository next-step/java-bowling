package bowling;

import bowling.domain.DownedPinCount;
import bowling.domain.game.Game;
import bowling.domain.game.Games;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
	public static void main(String[] args) {
		Games games = InputView.prepareGames();
		OutputView.showInitializedGame(games);

		while (!games.isAllGameFinished()) {
			games.getGames()
					.forEach(game -> playEachGame(game, games));
		}
	}

	private static void playEachGame(Game game, Games games) {
		boolean frameIsDone = false;
		while (!frameIsDone && !game.isGameFinished()) {
			DownedPinCount currentFramePitch = InputView.getCurrentFramePitch(game.getPlayersName());
			frameIsDone = game.play(currentFramePitch);
			OutputView.showDashBoard(games);
		}
	}
}
