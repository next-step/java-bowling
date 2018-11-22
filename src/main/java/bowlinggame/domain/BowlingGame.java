package bowlinggame.domain;

import bowlinggame.domain.frame.Frame;
import bowlinggame.dto.PlayerResultDto;

public class BowlingGame {

	private int currentFrame;
	private Player player;

	public BowlingGame(String player) {
		this.currentFrame = Frame.FIRST_FRAME;
		this.player = new Player(player);
	}

	public PlayerResultDto play(int pinCount) {
		try {
			return player.roll(pinCount);
		} catch (IllegalStateException e) {
			return player.getPlayerResult();
		}
	}

	public PlayerResultDto result() {
		return player.getPlayerResult();
	}

	public boolean isGameOver() {
		return player.isFrameOver(Frame.LAST_FRAME);
	}

	public int getCurrentFrame() {
		if (player.isFrameOver(currentFrame)) {
			++currentFrame;
		}
		return currentFrame;
	}
}
