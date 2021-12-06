package bowling.domain;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingGame {
	private final Player player;
	private final Frames frames;

	private BowlingGame(Player player, Frames frames) {
		this.player = player;
		this.frames = frames;
	}

	public static BowlingGame create(Player player) {
		return new BowlingGame(player, Frames.initialize());
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

	public boolean isFrameRunning(Index index) {
		return frames.isFrameRunning(index);
	}

	public List<Frame> getFrames() {
		return frames.getValues();
	}
}
