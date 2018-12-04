package domain;

import domain.frame.Frame;
import domain.frame.FrameResults;

/**
 * Created by hspark on 22/11/2018.
 */
public class Bowling {
	private Player player;
	private Frame frame = Frame.first();

	public Bowling(Player player) {
		this.player = player;
	}

	public void bowl(Pin pin) {
		Frame lastFrame = frame.getLastFrame();
		lastFrame.pitch(pin);
	}

	public boolean hasNext() {
		return frame.getLastFrame().isLeftFrame();
	}

	public int getNextFrameNumber() {
		return frame.getLastFrame().getFrameNumber();
	}

	public FrameResults getFrameResults() {
		return frame.getFrameResults();
	}

	public BowlingScoreBoard getBowlingScoreBoard() {
		return new BowlingScoreBoard(player, frame.getFrameResults());
	}
}
