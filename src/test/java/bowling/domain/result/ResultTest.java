package bowling.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class ResultTest {

	public static Stream<Arguments> 투구_점수의_반환_예시() {
		return Stream.of(
			Arguments.of(10, 0, Result.STRIKE),
			Arguments.of(0, 10, Result.SPARE),
			Arguments.of(5, 5, Result.SPARE),
			Arguments.of(1, 1, Result.MISS),
			Arguments.of(0, 1, Result.MISS),
			Arguments.of(0, 0, Result.GUTTER)
		);
	}

	@DisplayName("투구 점수를 받아 점수를 반환한다.")
	@ParameterizedTest
	@MethodSource("투구_점수의_반환_예시")
	void 투구_점수를_받아_결과를_반환한다(int score1, int score2, Result expected) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);
		Scores scores = Scores.from(first);
		scores.addSecondScore(second);
		Result result = Result.findByScores(scores.getFirst(), scores.getSecond());
		assertThat(result).isEqualTo(expected);
	}
}
