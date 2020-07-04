package bowling;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.board.Board;
import bowling.domain.board.Boards;
import bowling.domain.frame.Frames;
import bowling.domain.player.Players;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

	public void playGame() {
		int playCount = InputView.askPlayerCount();
		Players players = InputView.askPlayer(playCount);
		Boards boards = createBoards(players);

		while (!boards.isAllPlayed()) {
			Board playingBoard = boards.getPlayingBoard();
			Score score = InputView.askScore(playingBoard.getPlayer());
			playFrame(boards, score);
			OutputView.printBoards(boards);
		}
	}

	public Boards createBoards(Players players) {
		List<Board> boards = new ArrayList<>();
		players.getPlayers()
			.forEach(player -> boards.add(Board.of(player, Frames.of())));
		return Boards.of(boards);
	}

	public void playFrame(Boards boards, Score score) {
		boards.playFrame(score);
	}
}
