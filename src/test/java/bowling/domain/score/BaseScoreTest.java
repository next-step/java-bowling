package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.domain.score.exception.InvalidScoreException;

@DisplayName("기본 점수")
class BaseScoreTest {

	@DisplayName("생성")
	@ParameterizedTest
	@ValueSource(ints = {
		0, 1, 10, 20, 30
	})
	void create(final int value) {
		// given

		// when
		final BaseScore score = BaseScore.of(value);

		// then
		assertThat(score.getValue()).isEqualTo(value);
		assertThat(score.isComputeAble()).isFalse();
	}

	@DisplayName("생성 - 유효하지 않은 점수")
	@ParameterizedTest
	@ValueSource(ints = {
		-1, 31
	})
	void create_invalid(final int value) {
		// given

		// when
		assertThrows(InvalidScoreException.class, () -> BaseScore.of(value));

		// then

	}

	@DisplayName("생성 - zero")
	@Test
	void createZero() {
		// given

		// when
		final int value = BaseScore.ofZero().getValue();

		// then
		assertThat(value).isZero();
	}

	@DisplayName("생성 - strike")
	@Test
	void createStrike() {
		// given

		// when
		final int value = BaseScore.ofStrike().getValue();

		// then
		assertThat(value).isEqualTo(10);
	}

	static Stream<Arguments> add() {
		return Stream.of(
			Arguments.of(BaseScore.of(0), BaseScore.of(10), 10),
			Arguments.of(BaseScore.of(10), BaseScore.of(0), 10),
			Arguments.of(BaseScore.of(2), BaseScore.of(4), 6),
			Arguments.of(BaseScore.of(5), BaseScore.of(2), 7)
		);
	}

	@DisplayName("점수끼리 더하기")
	@ParameterizedTest
	@MethodSource
	void add(final BaseScore score1, final BaseScore score2, final int expected) {
		// given

		// when
		final Score result = score1.add(score2);

		// then
		assertThat(result.getValue()).isEqualTo(expected);
	}
}
