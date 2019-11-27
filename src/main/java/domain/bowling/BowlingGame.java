package domain.bowling;

import domain.BowlingPins;
import domain.UserName;

import java.util.Collections;
import java.util.List;

/**
 * 볼링 게임 하나를 의미
 */
public class BowlingGame {

	private static final int START_TURN_INDEX = 0;

	private int gameTurnIndex;
	// TODO: 2019-11-25 일급 컬렉션
	private List<BowlingOnBoard> bowlingOnBoards;

	public BowlingGame(UserName userName) {
		this.gameTurnIndex = START_TURN_INDEX;
		this.bowlingOnBoards = Collections.singletonList(new BowlingOnBoard(userName));
	}

	public void roll(BowlingPins pins) {
		bowlingOnBoards.get(gameTurnIndex).roll(pins);
	}

	public boolean isNotEnd() {
		return bowlingOnBoards.stream()
				.anyMatch(BowlingOnBoard::isNotEnd);
	}

	public int getCurrentFrameIndex() {
		return bowlingOnBoards.get(gameTurnIndex).getCurrentFrame();
	}

	public BowlingOnBoard getBowlingOnBoard() {
		return bowlingOnBoards.get(gameTurnIndex);
	}

}
