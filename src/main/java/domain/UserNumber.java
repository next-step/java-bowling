package domain;

public class UserNumber {

	private static final int MIN_USER_NUMBER = 1;

	private final int userNumber;

	private UserNumber(int userNumber) {
		validateUserName(userNumber);
		this.userNumber = userNumber;
	}

	private void validateUserName(int userNumber) {
		if (userNumber < MIN_USER_NUMBER) {
			throw new IllegalArgumentException(String.format("게임에 참여하는 유저 수(%s)는 최소 %s명 이상이어야 합니다",
					userNumber, MIN_USER_NUMBER));
		}
	}

	public static UserNumber of(int userNumber) {
		return new UserNumber(userNumber);
	}

	public boolean isExceedUserNumber(int targetNumber) {
		return targetNumber >= userNumber;
	}

	public int getUserNumber() {
		return userNumber;
	}

}
