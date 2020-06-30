package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {

	@DisplayName("점수가 0 이상이고 10 이하이면 점수가 성공적으로 생성된다.")
	@ValueSource(ints = {0, 5, 10})
	@ParameterizedTest
	void 점수는_0_이상이고_10_이하이다(int score) {
		assertThatCode(
			() -> Score.ofScore(score)
		).doesNotThrowAnyException();
	}

	@DisplayName("점수가 0 미만이고 10 초과이면 오류를 발생시킨다.")
	@ValueSource(ints = {- 1, 11, 19})
	@ParameterizedTest
	void 점수_생성시_오류가_반환된다(int score) {
		assertThatCode(
			() -> Score.ofScore(score))
			.as("wrong score")
			.isInstanceOf(IllegalArgumentException.class);
	}
}
