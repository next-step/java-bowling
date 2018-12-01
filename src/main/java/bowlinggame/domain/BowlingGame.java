package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;

public class BowlingGame {

	private FrameNumber currentFrameNumber;
	private Player player;

	public BowlingGame(String player) {
		this.currentFrameNumber = FrameNumber.first();
		this.player = Player.of(player);
	}

	public PlayerResult play(int pinCount) {
		try {
			return player.roll(pinCount);
		} catch (IllegalStateException e) {
			return result();
		}
	}

	public PlayerResult result() {
		return player.getPlayerResult();
	}

	public boolean isGameOver() {
		return player.isFrameOver(currentFrameNumber);
	}

	public FrameNumber getCurrentFrameNumber() {
		if (player.isFrameOver(currentFrameNumber)) {
			currentFrameNumber = currentFrameNumber.next();
		}
		return currentFrameNumber;
	}
}
