package domain.frame;

import domain.phase.NormalPhase;
import domain.phase.result.NormalPhaseResult;
import domain.phase.result.PhaseResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class NormalFrameTest {

	@Test
	void 처음에_10개를_모두치면_스트라이크이다() {
		// given
		NormalFrame frame = new NormalFrame();

		// when
		PhaseResult result = frame.shoot(10);

		// then
		assertThat(result).isEqualTo(NormalPhaseResult.STRIKE);
	}

	@Test
	void 두번째에_남아있는_볼링핀을_모두치면_스페어이다() {
		// given
		NormalFrame frame = new NormalFrame();

		// when
		frame.shoot(4);
		PhaseResult result = frame.shoot(6);

		// then
		assertThat(result).isEqualTo(NormalPhaseResult.SPARE);
	}

	@Test
	void 두번째에도_남아있는_볼링핀을_모두_못치면_미스이다() {
		// given
		NormalFrame frame = new NormalFrame();

		// when
		frame.shoot(4);
		PhaseResult result = frame.shoot(5);

		// then
		assertThat(result).isEqualTo(NormalPhaseResult.MISS);
	}

	@Test
	void 하나의_핀도_못치면_거터이다() {
		// given
		NormalFrame frame = new NormalFrame();

		// when
		frame.shoot(0);
		PhaseResult result = frame.shoot(0);

		// then
		assertThat(result).isEqualTo(NormalPhaseResult.GUTTER);
	}

	@Test
	void 존재하는_핀보다_더많은_핀을_넘어뜨릴수_없다() {
		// given
		NormalFrame frame = new NormalFrame();

		// when & then
		assertThatThrownBy(() -> {
			frame.shoot(11);
		})
		.isInstanceOf(IllegalArgumentException.class);
	}

}
