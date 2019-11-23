package domain.frame;

import domain.phase.result.FinalPhaseResult;
import domain.phase.result.PhaseResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class FinalFrameTest {

	@Test
	void 두번째_페이즈에서_스페어를_처리하면_세번째_페이즈까지_칠_수_있다() {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.shoot(4);
		finalFrame.shoot(6);
		PhaseResult result = finalFrame.shoot(8);

		// then
		assertThat(result).isEqualTo(FinalPhaseResult.LAST_SCORE);
	}

	@Test
	void 첫_번째_페이즈에서_스트라이크를_치면_세번째_페이즈까지_칠_수_있다() {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.shoot(10);
		finalFrame.shoot(6);
		PhaseResult result = finalFrame.shoot(3);

		// then
		assertThat(result).isEqualTo(FinalPhaseResult.LAST_SCORE);
	}

	@Test
	void 연달아_스트라이크를_치면_세번째_페이즈까지_칠_수_있다() {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.shoot(10);
		finalFrame.shoot(10);
		PhaseResult result = finalFrame.shoot(6);

		// then
		assertThat(result).isEqualTo(FinalPhaseResult.LAST_SCORE);
	}

	@Test
	void 두번째_페이즈까지_스페어를_못치면_세번째_페이즈를_갈_수_없다() {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.shoot(4);
		finalFrame.shoot(5);

		// then
		assertThatThrownBy(() -> {
			finalFrame.shoot(6);
		})
		.isInstanceOf(IllegalArgumentException.class);
	}

}
