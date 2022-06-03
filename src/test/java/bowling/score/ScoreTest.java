package bowling.score;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

	@Test
	void 동등성_테스트() {
		assertThat(Score.of(1, 1)).isEqualTo(Score.of(1, 1));
	}

	@ParameterizedTest(name = "보너스 카운트가 {0} 이면 {1}")
	@CsvSource(
		delimiter = ':',
		value = {"0:true", "1:false"})
	void 계산_가능_여부(int bonusCount, boolean result) {
		assertThat(Score.of(1, bonusCount).canScore()).isEqualTo(result);
	}

	@Test
	void 점수_누적_테스트() {
		Score base = Score.of(10, 1);
		Score operand = Score.of(10, 0);

		Score expect = Score.of(20, 0);

		assertThat(base.accumulate(operand)).isEqualTo(expect);
	}

}