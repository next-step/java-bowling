package bowling.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bowling.model.frame.Frames;

public class BowlGame {

	private final List<Player> players;
	private int currentPlayerIndex;

	private BowlGame(List<Player> players) {
		this.players = players;
		this.currentPlayerIndex = 0;
	}

	public static BowlGame createBowlGame(String[] players) {
		return new BowlGame(Arrays.stream(players)
			.map(playerName -> new Player(new Name(playerName), Frames.initCreateFrames()))
			.collect(Collectors.toList()));
	}

	public boolean isContinueGame() {
		return players.stream()
			.anyMatch(player -> player.getFrames().isContinueGame());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player turnPlayer() {
		return players.get(currentPlayerIndex);
	}

	public boolean playBowling(int inputStrikeNumber) {
		boolean endGame = players.get(currentPlayerIndex)
			.getFrames()
			.playBowling(inputStrikeNumber);
		if (endGame) {
			currentPlayerIndex = findTurnOfPlay(currentPlayerIndex);
		}
		return endGame;
	}

	public int findTurnOfPlay(int currentPlayer) {
		if (currentPlayer + 1 >= players.size()) {
			return 0;
		}
		return currentPlayer + 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BowlGame bowlGame = (BowlGame)o;
		return Objects.equals(players, bowlGame.players);
	}

	@Override
	public int hashCode() {
		return Objects.hash(players);
	}

}
