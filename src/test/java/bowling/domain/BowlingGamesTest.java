package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGamesTest {
	@DisplayName("초기 상태 검증")
	@Test
	void create() {
		// given
		List<Player> players = Arrays.asList(Player.create("AAA"), Player.create("BBB"), Player.create("CCC"));

		// when
		BowlingGames bowlingGames = BowlingGames.create(players);

		// then
		assertAll(
			() -> assertThat(bowlingGames.hasNextPitching()).isTrue(),
			() -> assertThat(bowlingGames.getValues()).hasSize(3),
			() -> assertThat(bowlingGames.getCurrentIndex()).isEqualTo(Index.first())
		);
	}
}
