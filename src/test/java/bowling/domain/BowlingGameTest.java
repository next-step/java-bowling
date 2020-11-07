package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BowlingGameTest {

	@Test
	void 볼링게임을_시작한다() {
		BowlingGame bowlingGame = new BowlingGame("JYP");
		assertThat(bowlingGame).isEqualTo(new BowlingGame("JYP"));
	}
}
