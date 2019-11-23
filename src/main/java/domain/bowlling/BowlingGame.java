package domain.bowlling;

import domain.UserName;

import java.util.Collections;
import java.util.List;

public class BowlingGame {

	private static final int FRAME_AFTER_GAME = 11;

	private static final int START_TURN_INDEX = 0;

	private int gameTurnIndex;
	private List<BowlingOnBoard> bowlingOnBoards;

	public BowlingGame(UserName userName) {
		this.gameTurnIndex = START_TURN_INDEX;
		this.bowlingOnBoards = Collections.singletonList(new BowlingOnBoard(userName));
	}

	public void shoot(int fallenBowlingPins) {
		bowlingOnBoards.get(gameTurnIndex).shoot(fallenBowlingPins);
	}

	public boolean isNotEnd() {
		// TODO: 2019-11-18 여러명이 게임을 할 때가 되면 로직 고민
		return bowlingOnBoards.get(bowlingOnBoards.size() - 1).getCurrentFrame() != FRAME_AFTER_GAME;
	}

	public int getCurrentFrame() {
		// TODO: 2019-11-18 여러명이 게임을 하는 요구사항이 있을 때 점검
		return bowlingOnBoards.get(gameTurnIndex).getCurrentFrame();
	}

	public BowlingOnBoard getBowlingOnBoard() {
		return bowlingOnBoards.get(gameTurnIndex);
	}

}
