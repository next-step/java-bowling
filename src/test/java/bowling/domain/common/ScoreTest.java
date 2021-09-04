package bowling.domain.common;

import static org.assertj.core.api.Assertions.*;

import bowling.domain.common.exception.InvalidCaculateScoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("점수")
class ScoreTest {

	@DisplayName("생성")
	@ParameterizedTest
	@CsvSource({
			"10,1"
	})
	void create(final int score, final int left) {
		// given

		// when
		final Score newScore = new Score(score, left);

		// then
		assertThat(newScore.getLeftCount()).isEqualTo(left);
	}

	@DisplayName("생성 - 유효하지 않음")
	@ParameterizedTest
	@CsvSource({
			"-1,0",
			"31,0",
			"0,-1",
			"0,4",
	})
	void create_invalid(final int score, final int left) {
		// given

		// when
		Assertions.assertThrows(InvalidCaculateScoreException.class, () -> new Score(score, left));

		// then

	}

	@DisplayName("계산")
	@ParameterizedTest
	@CsvSource({
			"5,0,5",
			"10,0,10",
	})
	void calculate(final int score, final int left, final int expected) {
		// given

		// when
		final Score newScore = new Score(score, left);

		// then
		assertThat(newScore.getLeftCount()).isEqualTo(left);
		assertThat(newScore.getValue()).isEqualTo(expected);
	}
}
