package bowling.domain.player;

import bowling.domain.player.exception.InvalidNameException;

public class Player {

	private static final int NAME_LENGTH = 3;

	private final String name;

	private Player(final String name) {
		createValidation(name);
		this.name = name;
	}

	private void createValidation(final String name) {
		if (name.length() != NAME_LENGTH) {
			throw new InvalidNameException();
		}
	}

	public static Player of(final String name) {
		return new Player(name);
	}

	public String getName() {
		return name;
	}
}
