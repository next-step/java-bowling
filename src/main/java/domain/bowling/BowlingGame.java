package domain.bowling;

import domain.BowlingPins;
import domain.UserName;
import domain.UserNumber;

import java.util.List;

/**
 * 볼링 게임 하나를 의미
 */
public class BowlingGame {

	private static final int START_TURN_INDEX = 0;

	private int gameTurnIndex;
	private BowlingOnBoards bowlingOnBoards;

	public BowlingGame(UserNumber userNumber) {
		this.gameTurnIndex = START_TURN_INDEX;
		this.bowlingOnBoards = new BowlingOnBoards(userNumber);
	}

	public void enroll(UserName userName) {
		bowlingOnBoards.enroll(userName);
	}

	public void roll(BowlingPins pins) {
		gameTurnIndex = bowlingOnBoards.roll(pins, gameTurnIndex);
	}

	public boolean isNotEnd() {
		return bowlingOnBoards.isNotEnd();
	}

	public UserName getUserName() {
		return bowlingOnBoards.getUserName(gameTurnIndex);
	}

	public List<BowlingOnBoard> getBowlingOnBoard() {
		return bowlingOnBoards.getBowlingOnBoard();
	}

}
