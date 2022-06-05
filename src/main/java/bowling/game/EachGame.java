package bowling.game;

import bowling.frame.Frames;
import bowling.player.Player;

public class EachGame {

	private final Player player;
	private final Frames frames;

	private EachGame(Player player, Frames frames) {
		this.player = player;
		this.frames = frames;
	}

	public static EachGame start(Player player) {
		return new EachGame(player, Frames.start());
	}

	public boolean isEnd() {
		return frames.isEnd();
	}

	public boolean isNowTurnFrameNumber(int gameFrameNumber) {
		return !isEnd() && frameNumber() == gameFrameNumber;
	}

	public void throwBowl(int throwCount) {
		frames.throwBowl(throwCount);
	}

	public int frameNumber() {
		return frames.frameNumber();
	}

	public String playerName() {
		return this.player.name();
	}

	public Frames frames() {
		return frames;
	}
}
