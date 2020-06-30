package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
}
