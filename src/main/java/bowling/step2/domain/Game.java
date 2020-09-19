package bowling.step2.domain;

public class Game {
	private Player player;
	private Score score;

	public Game(Player player, Score score) {
		this.player = player;
		this.score = score;
	}

	public static Game startGame(String playerName) {
		return new Game(new Player(playerName), Score.init());
	}

	public void bowlingEachFrame() {
		// frame에서 공굴리기 하고 return Frame
	}
	public void bowlingFinalFrame() {
		// 마지막 frame 공굴리기 하고 return Frame
	}

	public String getPlayerName() {
		return player.toString();
	}

}
