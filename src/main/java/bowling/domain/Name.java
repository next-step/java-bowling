package bowling.domain;

import java.util.regex.Pattern;

import bowling.domain.exception.NameCreateException;

public class Name {

	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

	private final String value;

	private Name(final String value) {
		nameValidation(value);
		this.value = value;
	}

	public static Name of(final String value) {
		return new Name(value);
	}

	private void nameValidation(final String value) {
		if (!NAME_PATTERN.matcher(value).matches()) {
			throw new NameCreateException();
		}
	}

	public String getValue() {
		return value;
	}
}
