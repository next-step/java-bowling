package bowling.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private final Map<Player, Frames> playerFrames = new LinkedHashMap<>();

	public Board(List<Player> players) {
		players.forEach(this::add);
	}

	public Board(Player player, Frames frames) {
		playerFrames.put(player, frames);
	}

	public void add(Player player) {
		playerFrames.put(player, new Frames());
	}

	public boolean isEnd() {
		return playerFrames.values()
				.stream()
				.allMatch(Frames::isEnd);
	}

	public Frames frameOf(Player player) {
		return playerFrames.get(player);
	}

	public Map<Player, Frames> value() {
		return playerFrames;
	}
}
