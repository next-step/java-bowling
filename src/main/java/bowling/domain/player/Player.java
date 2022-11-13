package bowling.domain.player;

import bowling.exception.BadRequestException;

public class Player {

	private static final int NAME_LENGTH = 3;
	private final String name;

	public Player(String name) {
		validateNameLength(name);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	private void validateNameLength(String name) {
		if (name.length() != NAME_LENGTH) {
			throw new BadRequestException("플레이어의 이름은 3글자입니다.");
		}
	}
}
