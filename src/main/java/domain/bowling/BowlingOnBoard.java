package domain.bowling;

import domain.BowlingPins;
import domain.UserName;
import domain.states.States;

import java.util.List;
import java.util.Optional;

/**
 * 한 사람에게 할당되는 볼링판 한 줄
 */
public class BowlingOnBoard {

	private final BowlingBoard bowlingBoard;
	private final UserName userName;

	BowlingOnBoard(UserName userName) {
		bowlingBoard = BowlingBoard.newInstance();
		this.userName = userName;
	}

	boolean roll(BowlingPins pins) {
		return bowlingBoard.roll(pins);
	}

	int getCurrentFrame() {
		return bowlingBoard.getCurrentFrameIndex() + 1;
	}

	boolean isNotEnd() {
		return !bowlingBoard.isEnd();
	}

	public List<States> getStates() {
		return bowlingBoard.getStates();
	}

	public UserName getUserName() {
		return userName;
	}

	public List<Optional<Integer>> getScores() {
		return bowlingBoard.getScores();
	}

}
