package domain;

public class BowlingGame {

	private static int START_PHASE = 0;

	private int currentFrame;
	private BowlingBoard bowlingBoard;

	public BowlingGame() {
		this.currentFrame = START_PHASE;
	}

	public void shoot(int fallenBowlingPins) {
		currentFrame = bowlingBoard.shoot(currentFrame, fallenBowlingPins);
		// TODO: 2019-11-17 여기서 현재까지의 결과를 리턴해야함
	}

}
