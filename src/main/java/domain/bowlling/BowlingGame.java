package domain.bowlling;

import java.util.Collections;
import java.util.List;

public class BowlingGame {

	private static final int START_TURN_INDEX = 0;

	private int gameTurnIndex;
	private List<BowlingOnBoard> bowlingOnBoards;

	public BowlingGame(String userName) {
		this.gameTurnIndex = START_TURN_INDEX;
		this.bowlingOnBoards = Collections.singletonList(new BowlingOnBoard(userName));
	}

}
