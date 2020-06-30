package bowling.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

	private static final Pattern NAME_PATTERN = Pattern.compile("^([a-zA-Z]){3}");
	private final String name;

	private Player(String name) {
		this.name = name;
	}

	public static Player ofName(String name) {
		validateName(name);
		return new Player(name);
	}

	private static void validateName(String name) {
		Matcher matcher = NAME_PATTERN.matcher(name);
		if (! matcher.matches()) {
			throw new IllegalArgumentException("wrong name");
		}
	}

	public String getName() {
		return name;
	}
}
