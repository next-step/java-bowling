package bowling.model;

import java.util.Objects;

import bowling.model.frame.Frames;

public class Player {

	private final Name name;
	private final Frames frames;

	public Player(Name name) {
		this.name = name;
		frames = Frames.initCreateFrames();
	}

	public Player(Name name, Frames frames) {
		this.name = name;
		this.frames = frames;
	}

	public String getPlayerName() {
		return name.getName();
	}

	public Frames getFrames() {
		return frames;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(name, player.name) && Objects.equals(frames, player.frames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, frames);
	}
}
