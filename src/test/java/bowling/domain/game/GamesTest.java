package bowling.domain.game;

import bowling.domain.DownedPinCount;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamesTest {

	private final Player player1 = new Player("TED");
	private final Player player2 = new Player("KTY");
	private Games games;

	@BeforeEach
	void setup() {
		games = new Games(List.of(player1, player2));
	}

	@DisplayName("객체 생성 테스트")
	@Test
	void construct() {
		assertThat(games).isEqualTo(new Games(List.of(player1, player2)));
	}

	@DisplayName("모든 사용자의 게임이 끝나면 true 반환 테스트")
	@Test
	void isAllGameFinished() {
		int halfFrameCountOfGame = 5;

		assertThat(games.isAllGameFinished()).isFalse();

		rollingStrikeGivenCount(halfFrameCountOfGame);
		assertThat(games.isAllGameFinished()).isFalse();

		int halfFrameCountPlusStrikeBonusCount = halfFrameCountOfGame + 2;
		rollingStrikeGivenCount(halfFrameCountPlusStrikeBonusCount);
		assertThat(games.isAllGameFinished()).isTrue();
	}

	private void rollingStrikeGivenCount(int count) {
		for(int i = 0; i < count ; i++) {
			games.getGames()
					.forEach(game -> game.play(DownedPinCount.ALL_PIN_DOWN));
		}
	}


}
