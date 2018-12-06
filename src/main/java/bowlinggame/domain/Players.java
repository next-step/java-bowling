package bowlinggame.domain;

import static java.util.stream.Collectors.toList;

import bowlinggame.domain.frame.FrameNumber;
import java.util.Arrays;
import java.util.List;

public class Players {

	private List<Player> players;

	private Players(List<Player> players) {
		this.players = players;
	}

	public static Players fromComma(String playerNames) {
		List<Player> players = Arrays.stream(playerNames.split(","))
				.map(playerName -> Player.of(playerName))
				.collect(toList());
		return of(players);
	}

	public static Players of(List<Player> players) {
		return new Players(players);
	}

	public Player getFirstPlayer() {
		if (players.isEmpty()) {
			throw new IllegalStateException("1명 이상의 플레이어가 필요합니다.");
		}

		return players.get(0);
	}

	public Player next(Player currentPlayer) {
		int nextIndex = players.indexOf(currentPlayer) + 1;
		if (nextIndex == players.size()) {
			return getFirstPlayer();
		}

		return players.get(nextIndex);
	}

	public boolean isFrameOverAllPlayer(FrameNumber frameNumber) {
		return players.stream()
				.allMatch(player -> player.isFrameOver(frameNumber));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Result getResult() {
		List<PlayerResult> result = players.stream()
				.map(Player::getPlayerResult)
				.collect(toList());
		return new Result(result);
	}
}
