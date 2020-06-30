package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.util.ScoreBound;

public class ScoresTest {

	@DisplayName("첫 번째와 두 번째 투구의 합이 10이 넘으면 오류를 반환한다.")
	@CsvSource({"3, 8", "5, 7", "9, 2"})
	@ParameterizedTest
	void 투구_간의_합은_10을_넘지_않는다(int score1, int score2) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);
		Scores scores = Scores.from(first);

		assertThatCode(
			() -> scores.addSecondScore(second))
			.as("first and second frames score have more than 10 scores. wrong score.")
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("두 번째 투구의 점수를 입력하지 않으면 오류를 반환한다.")
	@ValueSource(ints = {1, 2, 3})
	@ParameterizedTest
	void 두_번째_투구_없으면_오류를_반환한다(int score) {
		Score first = Score.ofScore(score);
		Scores scores = Scores.from(first);
		assertThatCode(
			() -> scores.checkResult())
			.as("scores does not have second score result!")
			.isInstanceOf(IllegalStateException.class);
	}

	@DisplayName("두 번째 투구의 점수를 입력하면 해당 프레임의 점수를 확인할 수 있다.")
	@CsvSource({"1, 9", "2, 8", "3, 7", "7, 3", "8, 2", "9, 1"})
	@ParameterizedTest
	void 두_번째_투구_있으면_점수를_획안한다(int score1, int score2) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);
		Scores scores = Scores.from(first);
		scores.addSecondScore(second);

		Result result = scores.checkResult();
		assertThatCode(
			() -> scores.checkResult()
		).doesNotThrowAnyException();
	}

	public static Stream<Arguments> 게임_결과들의_모음() {
		Score TEN = Score.ofScore(ScoreBound.MAXIMUM_SCORE_BOUND.getBound());
		Score FIVE = Score.ofScore(5);
		Score ZERO = Score.ofScore(ScoreBound.MINIMUM_SCORE_BOUND.getBound());

		Scores scores1 = Scores.from(TEN);
		Result result1 = scores1.checkResult();

		Scores scores2 = Scores.from(FIVE);
		scores2.addSecondScore(FIVE);
		Result result2 = scores2.checkResult();

		Scores scores3 = Scores.from(FIVE);
		scores3.addSecondScore(ZERO);
		Result result3 = scores3.checkResult();

		Scores scores4 = Scores.from(ZERO);
		scores4.addSecondScore(ZERO);
		Result result4 = scores4.checkResult();

		return Stream.of(
			Arguments.of(result1, Result.STRIKE),
			Arguments.of(result2, Result.SPARE),
			Arguments.of(result3, Result.MISS),
			Arguments.of(result4, Result.GUTTER));
	}

	@DisplayName("Scores에서 게임 결과를 확인하여 반환한다.")
	@MethodSource("게임_결과들의_모음")
	@ParameterizedTest
	void 게임_결과를_반환한다(Result result, Result expected) {
		assertThat(result).isEqualTo(expected);
	}
}
