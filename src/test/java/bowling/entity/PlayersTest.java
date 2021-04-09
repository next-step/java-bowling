package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayersTest {

	@Test
	@DisplayName("기본 생성후 isKeepGoing 확인")
	void test() {
		Players players = new Players(Arrays.asList("AB", "BC"));

		assertThat(players.isKeepGoing())
			.isEqualTo(true);
	}

	@Test
	@DisplayName("player가 전부 수행후 keepGoing 확인")
	void player_keep_going_done() {
		// given
		Players players = new Players(Arrays.asList("AB", "BC"));


		// when
		IntStream.rangeClosed(0, 11)
			.forEach(key -> {
				players.getPlayers()
					.forEach(player -> {
						player.addPlayerFrameScore(10);
					});
			});

		assertThat(players.isKeepGoing())
			.isEqualTo(false);
	}

	@ParameterizedTest
	@DisplayName("player가 아직 전부 수행하지 않은 케이스 확인")
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void player_keep_going_isgoing(int input) {
		// given
		Players players = new Players(Arrays.asList("AB", "BC"));


		// when
		IntStream.rangeClosed(0, input)
			.forEach(key -> {
				players.getPlayers()
					.forEach(player -> {
						player.addPlayerFrameScore(10);
					});
			});

		assertThat(players.isKeepGoing())
			.isEqualTo(true);
	}
}
