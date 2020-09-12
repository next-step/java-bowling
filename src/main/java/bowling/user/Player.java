package bowling.user;

import org.apache.logging.log4j.util.Strings;

import bowling.exception.PlayerNameException;

public class Player {

	private static final int PLAYER_NAME_LENGTH = 3;
	private final String name;

	private Player(String name) {
		this.name = name;
	}

	public static Player of(String name) {
		if (Strings.isBlank(name)) {
			throw new PlayerNameException("플레이어 이름은 필수입니다.");
		}
		if (name.length() != PLAYER_NAME_LENGTH) {
			throw new PlayerNameException(String.format("플레이어 이름은 %d글자로 해주세요.", PLAYER_NAME_LENGTH));
		}

		return new Player(name);
	}

	public String getName() {
		return name;
	}
}
