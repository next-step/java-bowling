package bowling.step2.domain;

public class Player {
	private final String name;

	public Player(String name) {
		validPlayerName(name);
		this.name = name;
	}

	private void validPlayerName(String name) {
		if (name.length() != 3) {
			throw new IllegalArgumentException("플레이어 이름은 3글자만 가능합니다.");
		}
	}
}
