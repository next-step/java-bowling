package bowling.domain;

public class Player {

	private final Name name;

	private Player(final Name name) {
		this.name = name;
	}

	public static Player of(final Name name) {
		return new Player(name);
	}

	public String getName() {
		return name.getValue();
	}
}
