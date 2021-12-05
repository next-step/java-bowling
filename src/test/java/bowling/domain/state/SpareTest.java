package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;
import bowling.domain.Score;

class SpareTest {
	private static final Pins FIVE = Pins.create(5);

	private State spare;

	@BeforeEach
	void beforeEach() {
		spare = Spare.create(FIVE, FIVE);
	}

	@DisplayName("볼링 실행 검증")
	@Test
	void bowl() {
		// when then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> spare.bowl(FIVE));
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// when
		boolean result = spare.isEnd();

		//then
		assertThat(result).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// when
		String symbol = spare.symbol();

		// then
		assertThat(symbol).isEqualTo("5|/");
	}

	@DisplayName("현재 상태의 스코어 반환 검증")
	@Test
	void score() {
		// when
		Score score = spare.score();

		// then
		assertThat(score).isEqualTo(Score.create(10, 1));
	}

	@DisplayName("추가 점수 계산을 위한 스코어 반환 검증")
	@Test
	void calculateAdditionalScore() {
		// given
		Score score = Score.create(10, 2);

		// when
		Score result = spare.calculateAdditionalScore(score);

		// then
		assertThat(result).isEqualTo(Score.create(20));
	}
}
