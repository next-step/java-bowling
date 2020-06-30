package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoresTest {

	@DisplayName("첫 번째와 두 번째 투구의 합이 10이 넘으면 오류를 반환한다.")
	@CsvSource({"3, 8", "5, 7", "9, 2"})
	@ParameterizedTest
	void 투구_간의_합은_10을_넘지_않는다(int score1, int score2) {
		Score first = Score.ofScore(score1);
		Score second = Score.ofScore(score2);
		Scores scores = Scores.ofScore(first);

		assertThatCode(
			() -> scores.addSecondScore(second))
			.as("first and second frames score have more than 10 scores. wrong score.")
			.isInstanceOf(IllegalArgumentException.class);
	}
}
