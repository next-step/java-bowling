package domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 05/12/2018.
 */
public class BowlingGamesTest {

	@Test
	public void test_다음플레이어() {
		BowlingGames bowlingGames = new BowlingGames(new String[] { "ABC", "DEF" });
		Bowling currentBowling = bowlingGames.getCurrentGame();
		Assertions.assertThat(currentBowling.getPlayer().toString()).isEqualTo("ABC");

		bowlingGames.bowl(Pin.TEN);
		Bowling nextBowling = bowlingGames.getCurrentGame();
		Assertions.assertThat(nextBowling.getPlayer().toString()).isEqualTo("DEF");
	}
}