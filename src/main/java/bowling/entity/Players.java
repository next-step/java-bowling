package bowling.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
	private final List<Player> players;

	public Players(List<String> names) {
		this.players = names
			.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}

	public int turn() {
		return players.get(0)
			.currentPlayerFrameIndex() + 1;
	}

	public boolean isKeepGoing() {
		return players.stream()
			.anyMatch(Player::isEnd);
	}

	public List<Player> getPlayers() {
		return players;
	}
}
