package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.score.exception.InvalidScoreException;

@DisplayName("진행 중인 점수")
class ProgressScoreTest {

	@DisplayName("생성")
	@ParameterizedTest
	@CsvSource({
		"0,1",
		"1,1",
		"2,2",
		"30,2",
	})
	void create(final int value, final int leftCount) {
		// given

		// when
		final ProgressScore score = ProgressScore.of(value, leftCount);

		// then
		assertThat(score.getValue()).isEqualTo(value);
		assertThat(score.isComputeAble()).isFalse();
	}

	@DisplayName("생성 - 유효하지 않은 남은 숫자")
	@ParameterizedTest
	@CsvSource({
		"30,3",
	})
	void create_invalid(final int value, final int leftCount) {
		// given

		// when
		assertThrows(InvalidScoreException.class, () -> ProgressScore.of(value, leftCount));

		// then

	}

	@DisplayName("생성 - spare")
	@Test
	void createSpare() {
		// given

		// when
		final int value = ProgressScore.ofSpare().getValue();

		// then
		assertThat(value).isEqualTo(10);
	}

	@DisplayName("생성 - strike")
	@Test
	void createStrike() {
		// given

		// when
		final int value = ProgressScore.ofStrike().getValue();

		// then
		assertThat(value).isEqualTo(10);
	}
}
