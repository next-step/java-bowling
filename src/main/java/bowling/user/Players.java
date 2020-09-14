package bowling.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.util.CollectionUtils;

import bowling.exception.PlayerNameException;

public class Players {
	private final List<Player> players;

	private Players(List<Player> players) {
		this.players = players;
	}

	public static Players of(List<String> players) {
		if (CollectionUtils.isEmpty(players)) {
			throw new PlayerNameException("참여하는 선수들의 이름을 입력해주세요.");
		}
		return new Players(IntStream.range(0, players.size())
									.mapToObj(i -> Player.of(players.get(i)))
									.collect(Collectors.toList()));
	}

	public int size() {
		return players.size();
	}

	public Player indexOf(int index) {
		return players.get(index);
	}
}
