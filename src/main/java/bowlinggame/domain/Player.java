package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.domain.frame.Game;
import bowlinggame.dto.PlayerResultDto;

public class Player {

	public static final int MAX_NAME_LENGTH = 3;

	private String name;
	private Game game;

	private Player(String name, Game game) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("이름은 3글자까지 가능합니다.");
		}

		this.name = name;
		this.game = game;
	}

	public static Player of(String name) {
		return of(name, new Game());
	}

	public static Player of(String name, Game game) {
		return new Player(name, game);
	}

	public PlayerResultDto roll(int pinCount) {
		game.record(pinCount);
		return getPlayerResult();
	}

	public PlayerResultDto getPlayerResult() {
		return game.result(name);
	}

	public boolean isFrameOver(FrameNumber frameNumber) {
		return game.isFrameOver(frameNumber);
	}
}
