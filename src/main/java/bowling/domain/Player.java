package bowling.domain;

import bowling.domain.exception.InvalidNameException;

public class Player {

	private static final int NAME_LENGTH = 3;

	private final String name;

	public Player(final String name) {
		if (name.length() != NAME_LENGTH) {
			throw new InvalidNameException();
		}

		this.name = name;
	}

	public String getName() {
		return name;
	}
}
