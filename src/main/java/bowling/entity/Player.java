package bowling.entity;

import java.util.Optional;

public class Player {
	private final Name name;
	private PlayerFrameScore playerFrameScore;

	public Player(Name name) {
		this.name = name;
	}

	public Player(String name) {
		this(new Name(name));
	}

	public String getName() {
		return name.getName();
	}

	public void addPlayerFrameScore(NormalFrame normalFrame) {
		this.playerFrameScore = Optional.ofNullable(playerFrameScore)
			.orElseGet(PlayerFrameScore::new)
			.addFrameScore(normalFrame);
	}

	public void addPlayerFrameScore(FinalFrame finalFrame) {
		this.playerFrameScore = Optional.ofNullable(playerFrameScore)
			.orElseGet(PlayerFrameScore::new)
			.addFrameScore(finalFrame);
	}

	public PlayerFrameScore getPlayerFrameScore() {
		return playerFrameScore;
	}
}
