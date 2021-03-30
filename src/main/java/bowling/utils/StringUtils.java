package bowling.utils;

import java.util.Optional;

public class StringUtils {
	private static final String ALPHABET_REGEX = "[a-zA-Z]+";

	public static boolean isAlphabet(String input) {
		return input.matches(ALPHABET_REGEX);
	}

	public static boolean isBlank(String input) {
		return Optional.ofNullable(input)
			.orElseGet(() -> "")
			.trim()
			.length() == 0;
	}
}
