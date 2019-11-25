package domain;

public class UserName {

	private final String name;

	private UserName(String name) {
		validateUserName(name);
		this.name = name;
	}

	private void validateUserName(String name) {
		if (name.length() != 3) {
			throw new IllegalArgumentException("이름은 세 글자로 입력해주세요!");
		}
	}

	public static UserName of(String name) {
		return new UserName(name);
	}

	@Override
	public String toString() {
		return name;
	}

}
