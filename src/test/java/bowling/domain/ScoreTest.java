package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

	@DisplayName("다른 점수와의 합을 계산할 수 있다.")
	@CsvSource({"1, 9", "2, 8"})
	@ParameterizedTest
	void 점수_간의_합을_계산한다(int score1, int score2) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);
		Score result = first.add(second);
		assertThat(result.getScore()).isEqualTo(Score.ofScore(score1 + score2).getScore());
	}

	@DisplayName("다른 점수보다 큰 지 확인한다.")
	@CsvSource({"2, 1, true", "1, 2, false"})
	@ParameterizedTest
	void 점수_간의_대소를_비교한다(int score1, int score2, boolean expected) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);

		boolean result = first.isGreaterThan(second);
		assertThat(result).isEqualTo(expected);
	}

	@DisplayName("첫 번째 투구의 점수가 10이면 두 번째 투구의 점수를 입력하지 않아도 점수를 확인할 수 있다.")
	@ValueSource(ints = 10)
	@ParameterizedTest
	void 첫_번째_투구의_점수가_10이면_점수를_확인할_수_있다(int score) {
		Score first = Score.ofScore(score);
		Scores scores = Scores.from(first);
		Result result = scores.checkResult();
		assertThatCode(
			() -> scores.checkResult()
		).doesNotThrowAnyException();
	}
}
