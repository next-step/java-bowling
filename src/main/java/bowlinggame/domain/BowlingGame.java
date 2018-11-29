package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.dto.PlayerResultDto;

public class BowlingGame {

	private FrameNumber currentFrameNumber;
	private Player player;

	public BowlingGame(String player) {
		this.currentFrameNumber = FrameNumber.first();
		this.player = Player.of(player);
	}

	public PlayerResultDto play(int pinCount) {
		try {
			return player.roll(pinCount);
		} catch (IllegalStateException e) {
			return result();
		}
	}

	public PlayerResultDto result() {
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
