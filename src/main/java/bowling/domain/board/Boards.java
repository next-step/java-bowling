package bowling.domain.board;

import java.util.Collections;
import java.util.List;

import bowling.domain.score.Score;

public class Boards {

	private final List<Board> boards;
	private int currentIndex;

	private Boards(List<Board> boards) {
		this.boards = boards;
	}

	public static Boards of(List<Board> boards) {
		return new Boards(boards);
	}

	public boolean isAllPlayed() {
		return boards.stream()
			.allMatch(Board::isAllPlayed);
	}

	public List<Board> getBoards() {
		return Collections.unmodifiableList(boards);
	}

	public void playFrame(Score score) {
		Board currentBoard = getPlayingBoard();
		int beforeIndex = currentBoard.getPlayingFrameIndex();
		currentBoard.playFrame(score);
		int afterIndex = currentBoard.getPlayingFrameIndex();
		adjustPlayingFrameIndex(beforeIndex, afterIndex);
	}

	private void adjustPlayingFrameIndex(int beforeIndex, int afterIndex) {
		if (beforeIndex == afterIndex) {
			return;
		}
		currentIndex++;
	}

	public Board getPlayingBoard() {
		return boards.get(currentIndex);
	}
}
