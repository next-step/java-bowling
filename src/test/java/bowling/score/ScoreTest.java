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

	@Test
	void 점수는_0_이상이어야한다() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> Score.of(-1, 0)
		);
	}

	@Test
	void 보너스카운트는_0_이상이어야한다() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> Score.of(0, -1)
		);
	}

	@ParameterizedTest(name = "보너스 카운트가 {0} 이면 {1}")
	@CsvSource(
		delimiter = ':',
		value = {"0:true", "1:false"}
	)
	void 보너스_카운트가_없고_계산이_아직_불가능한_상태가_아니라면_계산_가능(int bonusCount, boolean result) {
		assertThat(Score.of(1, bonusCount).canScore()).isEqualTo(result);
	}

	@Test
	void 계산이_불가능한_값을_가지면_계산_불가() {
		assertThat(Score.unavailable().canScore()).isFalse();
	}

	@Test
	void 점수_누적_테스트() {
		Score base = Score.of(10, 1);
		Score operand = Score.of(10, 0);

		Score expect = Score.of(20, 0);

		assertThat(base.accumulate(operand)).isEqualTo(expect);
	}

}