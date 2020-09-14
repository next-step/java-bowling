package bowling.score;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

import bowling.exception.PlayersEmptyException;
import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.user.Player;
import bowling.user.Players;

public class ScoringBoards {
	private final List<ScoringBoard> scoringBoards;
	private int turnIndex;

	private ScoringBoards(List<ScoringBoard> scoringBoards) {
		this.scoringBoards = scoringBoards;
	}

	public static ScoringBoards of(Players players) {
		if (players == null || players.size() == 0) {
			throw new PlayersEmptyException("점수판을 만들기 위해서는 플레이어가 있어야 합니다.");
		}
		return new ScoringBoards(IntStream.range(0, players.size())
										  .mapToObj(i -> ScoringBoard.of(players.indexOf(i)))
										  .collect(toList()));
	}

	public boolean isEnd() {
		return scoringBoards.stream()
							.allMatch(scoringBoard -> scoringBoard.isEnd());
	}

	public List<Frames> reflect(Pins pins) {
		ScoringBoard currentScoringBoard = scoringBoards.get(turnIndex);
		Frames frames = currentScoringBoard.reflect(pins);
		if (frames.finishCurrentFrame()) {
			frames.moveNextFrame();
			moveNextTurn();
		}
		return getAllFrames();
	}

	private List<Frames> getAllFrames() {
		return scoringBoards.stream()
							.map(scoringBoard -> scoringBoard.getFrames())
							.collect(toList());
	}

	private void moveNextTurn() {
		if (turnIndex == scoringBoards.size() - 1) {
			turnIndex = 0;
			return;
		}
		++turnIndex;
	}

	public Player getCurrentPlayer() {
		ScoringBoard currentScoringBoard = scoringBoards.get(turnIndex);
		return currentScoringBoard.getPlayer();
	}
}
