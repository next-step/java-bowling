package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import bowling.execption.PlayerNameSizeException;

public class Player {
	private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]{3}$");

	private final String name;

	private Player(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (name == null || !NAME_REGEX.matcher(name).matches()) {
			throw new PlayerNameSizeException();
		}
	}

	public static Player create(String name) {
		return new Player(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Player player = (Player)obj;

		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
