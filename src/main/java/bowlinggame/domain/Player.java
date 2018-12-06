package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.domain.frame.Frames;
import java.util.Objects;

public class Player {

	public static final int MAX_NAME_LENGTH = 3;

	private String name;
	private Frames frames;

	private Player(String name, Frames frames) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("이름은 3글자까지 가능합니다.");
		}

		this.name = name;
		this.frames = frames;
	}

	public static Player of(String name) {
		return of(name, new Frames());
	}

	public static Player of(String name, Frames frames) {
		return new Player(name, frames);
	}

	public PlayerResult roll(int pinCount) {
		frames.record(pinCount);
		return getPlayerResult();
	}

	public PlayerResult getPlayerResult() {
		return frames.result(name);
	}

	public boolean isFrameOver(FrameNumber frameNumber) {
		return frames.isFrameOver(frameNumber);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Player)) {
			return false;
		}
		Player player = (Player) o;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
