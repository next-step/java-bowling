package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import java.util.Arrays;
import java.util.function.Function;

import bowling.exception.UserException;
import bowling.utils.StringUtils;

public class Name {
	private final String name;

	public Name(String name) {
		Validator.validate(name);
		this.name = name;
	}

	private static boolean checkNameSize(String input) {
		return input.length() > 3;
	}

	private static boolean isNotAlphabet(String input) {
		return !StringUtils.isAlphabet(input);
	}

	private enum Validator {
		IS_NULL(StringUtils::isBlank),
		NAME_SIZE_CHECK(Name::checkNameSize),
		ALPHABET_CHECK(Name::isNotAlphabet);
		private final Function<String, Boolean> func;

		Validator(Function<String, Boolean> func) {
			this.func = func;
		}

		public static void validate(String input) {
			Arrays.stream(values())
				.filter(validator -> validator.func.apply(input))
				.findFirst()
				.ifPresent((validator) -> {
					throw new UserException(NAME_EXCEPTION);
				});
		}
	}

	public String getName() {
		return name;
	}
}
