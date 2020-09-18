package bowling.step2.domain;

import bowling.step2.domain.frame.Frames;

public class Game {
	private Player player;
	private Frames frames;

	public Game(Player player, Frames frames) {
		this.player = player;
		this.frames = frames;
	}

	public static Game startGame(String playerName) {
		return new Game(new Player(playerName), new Frames());
	}

	public void bowlingEachFrame() {

	}
}
