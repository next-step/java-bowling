package bowling.player;

import java.util.Objects;

import bowling.util.ErrorTarget;
import bowling.util.Validator;

public class Player {

	private static final int PLAYER_NAME_LENGTH = 3;

	private final String name;

	public Player(String name) {
		Validator.notBlank(name, ErrorTarget.PlAYER);
		Validator.equivalent(PLAYER_NAME_LENGTH, name.length(),
			String.format("플레이어의 이름은 %d 글자여야 합니다. 입력 : %s", PLAYER_NAME_LENGTH, name));

		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
