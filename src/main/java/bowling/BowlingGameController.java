package bowling;

import bowling.domain.board.Board;
import bowling.domain.board.Boards;
import bowling.domain.player.Players;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

	private final BowlingGame bowlingGame;

	public BowlingGameController(BowlingGame bowlingGame) {
		this.bowlingGame = bowlingGame;
	}

	public void playGame() {
		int playCount = InputView.askPlayerCount();
		Players players = InputView.askPlayer(playCount);
		Boards boards = bowlingGame.startGame(players);

		while (! boards.isAllPlayed()) {
			Board playingBoard = boards.getPlayingBoard();
			Score score = InputView.askScore(playingBoard.getPlayer());
			bowlingGame.playFrame(boards, score);
			OutputView.printBoards(boards);
		}
	}
}
