package domain.bowlling;

import domain.frame.result.FrameResult;
import domain.phase.result.PhaseResult;

import java.util.List;

public class BowlingGame {

	@SuppressWarnings("FieldCanBeLocal")
	private static int START_FRAME_INDEX = 0;
	@SuppressWarnings("FieldCanBeLocal")
	private static int FINAL_FRAME_INDEX = 9;

	private int currentFrameIndex;
	private BowlingBoard bowlingBoard;

	public BowlingGame() {
		this.currentFrameIndex = START_FRAME_INDEX;
		this.bowlingBoard = new BowlingBoard();
	}

	public List<FrameResult> shoot(int fallenBowlingPins) {
		validateCurrentFrame();
		currentFrameIndex = bowlingBoard.shoot(currentFrameIndex, fallenBowlingPins);
		return bowlingBoard.getPhaseResults();
	}

	private void validateCurrentFrame() {
		if (this.currentFrameIndex > FINAL_FRAME_INDEX) {
			throw new IllegalStateException("종료된 볼링 게임입니다");
		}
	}

}
