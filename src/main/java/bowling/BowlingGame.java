package bowling;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.board.Board;
import bowling.domain.board.Boards;
import bowling.domain.frame.Frames;
import bowling.domain.player.Players;
import bowling.domain.score.Score;

public class BowlingGame {

	public Boards startGame(Players players) {
		List<Board> boards = new ArrayList<>();
		players.getPlayers()
			.forEach(player -> boards.add(Board.of(player, Frames.of())));
		return Boards.of(boards);
	}

	public void playFrame(Boards boards, Score score) {
		boards.playFrame(score);
	}
}
