package bowling.domain.board;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

public class Board {

	private final Player player;
	private final Frames frames;

	private Board(Player player, Frames frames) {
		this.player = player;
		this.frames = frames;
	}

	public static Board of(Player player, Frames frames) {
		return new Board(player, frames);
	}

	public boolean isAllPlayed() {
		return frames.isAllPlayed();
	}

	public void playFrame(Score score) {
		frames.playFrame(score);
	}

	public int getPlayingFrameIndex() {
		return frames.getPlayingFrameIndex();
	}

	public Player getPlayer() {
		return player;
	}

	public Frames getFrames() {
		return frames;
	}
}
