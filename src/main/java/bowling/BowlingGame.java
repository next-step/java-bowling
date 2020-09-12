package bowling;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.score.ScoringBoard;
import bowling.user.Player;

public class BowlingGame {

	private final ScoringBoard scoringBoard;

	private BowlingGame(ScoringBoard scoringBoard) {
		this.scoringBoard = scoringBoard;
	}

	public static BowlingGame init(Player player) {
		return new BowlingGame(ScoringBoard.of(player));
	}

	public boolean isEndGame() {
		return scoringBoard.isEnd();
	}

	public Frames pitchBall(Pins pins) {
		return scoringBoard.reflect(pins);
	}

	public int getCurrentFrameNo() {
		return scoringBoard.getCurrentFrameNo();
	}

	public Player getPlayer() {
		return scoringBoard.getPlayer();
	}

}
