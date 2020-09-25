package bowling.domain.game;

import bowling.domain.player.Player;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Games {

	private final List<Game> games;

	public Games(List<Player> players) {
		this.games = players.stream()
						.map(Game::new)
						.collect(Collectors.toList());

	}

	public List<Game> getGames() {
		return games;
	}

	public boolean isAllGameFinished() {
		return games.stream()
				.allMatch(Game::isGameFinished);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Games games1 = (Games) o;
		return games.equals(games1.games);
	}

	@Override
	public int hashCode() {
		return Objects.hash(games);
	}
}
