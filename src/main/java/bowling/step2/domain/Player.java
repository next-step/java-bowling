package bowling.step2.domain;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return name;
	}
}
