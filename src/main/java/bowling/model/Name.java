package bowling.model;

import java.util.Objects;

public class Name {
	private static final String TYPE_MATCH_ERROR_MESSAGE = "이름은 영문만 가능합니다.";
	private static final String LIMIT_NAME_ERROR_MESSAGE = "이름은 3글자만 허용 됩니다.";
	private static final String NULL_ERROR_MESSAGE = "이름이 null 입니다.";
	private static final String EMPTY_ERROR_MESSAGE = "이름이 빈값 입니다.";
	private static final String REG_EXP = "^[a-zA-Z]*$";
	private static final int LIMIT_LENGTH = 3;

	private final String name;

	public Name(String name) {
		checkNameNull(name);
		checkNameEmpty(name);
		checkNameLength(name);
		checkNameTypeMatch(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void checkNameNull(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NULL_ERROR_MESSAGE);
		}
	}

	private void checkNameEmpty(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
		}
	}

	private void checkNameLength(String name) {
		if (name.length() != LIMIT_LENGTH) {
			throw new IllegalArgumentException(LIMIT_NAME_ERROR_MESSAGE);
		}
	}

	private void checkNameTypeMatch(String name) {
		if (!name.matches(REG_EXP)) {
			throw new IllegalArgumentException(TYPE_MATCH_ERROR_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Name name1 = (Name)o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
