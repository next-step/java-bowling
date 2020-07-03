package bowling.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.util.ResultUtil;

public class ResultTest {

	public static Stream<Arguments> 투구_점수의_반환_예시() {
		return Stream.of(
			Arguments.of(10, 0, new Strike()),
			Arguments.of(0, 10, new Spare()),
			Arguments.of(5, 5, new Spare()),
			Arguments.of(1, 1, new Miss()),
			Arguments.of(0, 1, new Miss()),
			Arguments.of(0, 0, new Gutter())
		);
	}

	@DisplayName("투구 점수를 받아 점수를 반환한다.")
	@ParameterizedTest
	@MethodSource("투구_점수의_반환_예시")
	void 투구_점수를_받아_결과를_반환한다(int score1, int score2, Result expected) {
		Score first = Score.of(score1);
		Score second = Score.of(score2);
		Scores scores = Scores.from(first);
		scores.playSecondHalf(second);
		Result result = ResultUtil.findByScores(scores.getFirst().get(), scores.getSecond().get());
		assertThat(result).isInstanceOf(expected.getClass());
	}
}
