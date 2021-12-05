package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;
import bowling.domain.Score;

class StrikeTest {
	private State strike;

	@BeforeEach
	void beforeEach() {
		strike = Strike.create();
	}

	@DisplayName("볼링 실행시 예외 발생")
	@Test
	void bowl() {
		// given
		Pins pins = Pins.create(5);

		// when then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> strike.bowl(pins));
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// when
		boolean result = strike.isEnd();

		//then
		assertThat(result).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// when
		String symbol = strike.symbol();

		// then
		assertThat(symbol).isEqualTo("X");
	}

	@DisplayName("현재 상태의 스코어 반환 검증")
	@Test
	void score() {
		// when
		Score score = strike.score();

		// then
		assertThat(score).isEqualTo(Score.create(10, 2));
	}

	@DisplayName("추가 점수 계산을 위한 스코어 반환 검증")
	@Test
	void calculateAdditionalScore() {
		// given
		Score score = Score.create(10, 2);

		// when
		Score result = strike.calculateAdditionalScore(score);

		// then
		assertThat(result).isEqualTo(Score.create(20, 1));
	}
}
