package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.domain.score.exception.InvalidScoreException;

@DisplayName("계산할 수 있는 점수")
class ComputableScoreTest {

	@DisplayName("생성")
	@ParameterizedTest
	@ValueSource(ints = {
		0, 1, 10, 20, 30
	})
	void create(final int value) {
		// given

		// when
		final ComputableScore score = ComputableScore.of(value);

		// then
		assertThat(score.getValue()).isEqualTo(value);
		assertThat(score.isComputeAble()).isTrue();
	}

	@DisplayName("생성 - 유효하지 않은 점수")
	@ParameterizedTest
	@ValueSource(ints = {
		-1, 31
	})
	void create_invalid(final int value) {
		// given

		// when
		assertThrows(InvalidScoreException.class, () -> ComputableScore.of(value));

		// then

	}

	@DisplayName("점수끼리 더하기 - 불가능")
	@Test
	void add_failed() {
		// given
		final ComputableScore score1 = ComputableScore.of(1);
		final ComputableScore score2 = ComputableScore.of(2);

		// when
		assertThrows(InvalidScoreException.class, () -> score1.add(score2));

		// then

	}
}
