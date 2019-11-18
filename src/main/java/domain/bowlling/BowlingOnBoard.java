package domain.bowlling;

import domain.frame.result.FrameResult;

import java.util.List;

/**
 * 한 사람에게 할당되는 볼링 점수판 한 줄
 */
public class BowlingOnBoard {

	private final BowlingBoard bowlingBoard = new BowlingBoard();
	private final String userName;

	BowlingOnBoard(String userName) {
		this.userName = userName;
	}

	void shoot(int fallenBowlingPins) {
		bowlingBoard.shoot(fallenBowlingPins);
	}

	int getCurrentFrame() {
		return bowlingBoard.getCurrentFrameIndex() + 1;
	}

	public List<FrameResult> getFrameResults() {
		return bowlingBoard.getPhaseResults();
	}

	public String getUserName() {
		return userName;
	}

}
