package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BowlingGameTest {

	private BowlingGame bowlingGame;

	@BeforeEach
	void setUp() {
		bowlingGame = new BowlingGame("JYP");
	}

	@Test
	void 볼링게임을_시작한다() {
		assertThat(bowlingGame).isEqualTo(new BowlingGame("JYP"));
	}

	@ParameterizedTest
	@CsvSource(value = {"10, 5, 5, 10, 3", "1, 1, 1, 1, 2", "10, 10, 10, 10, 4"})
	void 볼링게임의_프레임개수를_알려준다(int first, int second, int third, int fourth, int expect) {
		bowlingGame.pitch(first);
		bowlingGame.pitch(second);
		bowlingGame.pitch(third);
		bowlingGame.pitch(fourth);
		assertThat(bowlingGame.getFrames()).hasSize(expect);
	}

	@ParameterizedTest
	@CsvSource(value = {"5, 6", "1, -1", "10, 12"})
	void 잘못된_피칭을_하면_오류를_반환한다(int first, int second) {
		bowlingGame.pitch(first);
		assertThatThrownBy(() -> bowlingGame.pitch(second))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
