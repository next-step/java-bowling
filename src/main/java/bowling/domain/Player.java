package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Player {
	private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]");
	private final String name;

	public Player(String name) {
		this.validateName(name);
		this.name = name;
	}

	private void validateName(String name){
		if (StringUtils.isEmpty(name) || !NAME_PATTERN.matcher(name).find() || name.length() != 3) {
			throw new IllegalArgumentException("이름은 반드시 세 글자로 입력하셔야 합니다.");
		}
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
