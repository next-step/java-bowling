package bowling;

import java.util.List;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.score.ScoringBoards;
import bowling.user.Player;
import bowling.user.Players;

public class BowlingGame {

	private final ScoringBoards scoringBoards;

	private BowlingGame(ScoringBoards scoringBoards) {
		this.scoringBoards = scoringBoards;
	}

	public static BowlingGame init(Players players) {
		return new BowlingGame(ScoringBoards.of(players));
	}

	public boolean isEndGame() {
		return scoringBoards.isEnd();
	}

	public List<Frames> pitchBall(Pins pins) {
		return scoringBoards.reflect(pins);
	}

	/*public int getCurrentFrameNo() {
		return scoringBoards.getCurrentFrameNo();
	}*/

	public Player getCurrentPlayer() {
		return scoringBoards.getCurrentPlayer();
	}

}
