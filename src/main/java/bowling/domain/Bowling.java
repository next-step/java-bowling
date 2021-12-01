package bowling.domain;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {
	private final Player player;
	private final Frames frames;

	private Bowling(Player player, Frames frames) {
		this.player = player;
		this.frames = frames;
	}

	public static Bowling create(Player player) {
		return new Bowling(player, Frames.initialize());
	}

	public void bowl(Pins pins) {
		frames.bowl(pins);
	}

	public String getPlayerName() {
		return player.toString();
	}

	public boolean hasNextPitching() {
		return frames.hasNextPitching();
	}

	public int getCurrentFrameIndex() {
		return frames.getLastFrameIndex();
	}

	public List<Frame> getFrames() {
		return frames.getValues();
	}
}
