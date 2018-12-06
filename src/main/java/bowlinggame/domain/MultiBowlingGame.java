package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;

public class MultiBowlingGame {

	private FrameNumber currentFrameNumber;
	private Players players;
	private Player currentPlayer;

	public MultiBowlingGame(String player) {
		this(Players.fromComma(player));
	}

	public MultiBowlingGame(Players players) {
		this.currentFrameNumber = FrameNumber.first();
		this.players = players;
		this.currentPlayer = players.getFirstPlayer();
	}

	public Result play(int pinCount) {
		currentPlayer.roll(pinCount);
		currentPlayer = nextPlayer();
		return result();
	}

	private Player nextPlayer() {
		if (players.isFrameOverAllPlayer(currentFrameNumber)) {
			currentFrameNumber = currentFrameNumber.next();
			return players.getFirstPlayer();
		}

		Player nextPlayer = players.next(currentPlayer);
		while (nextPlayer.isFrameOver(currentFrameNumber)) {
			return players.next(nextPlayer);
		}
		return nextPlayer;
	}

	public Result result() {
		return players.getResult();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public FrameNumber getCurrentFrameNumber() {
		return currentFrameNumber;
	}

	public boolean isGameOver() {
		return players.isFrameOverAllPlayer(FrameNumber.last());
	}
}
