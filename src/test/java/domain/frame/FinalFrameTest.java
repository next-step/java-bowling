package domain.frame;

import domain.states.BowlingPins;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FinalFrame : FinalFrameStates 와 Score 를 가지고 있다.
 *
 */
@SuppressWarnings("NonAsciiCharacters")
class FinalFrameTest {

	@Test
	void 끝나지않은_점수는_계산할_수_없다() {
		// given
		Frame frame = FinalFrame.newInstance();

		// when
		frame.roll(BowlingPins.of(3));

		// then
		assertThat(frame.getOptionalScore()).isEqualTo(Optional.empty());
	}

	@Test
	void 미스는_바로_점수가_계산된다() {
		// given
		Frame frame = FinalFrame.newInstance();

		// when
		frame.roll(BowlingPins.of(3));
		frame.roll(BowlingPins.of(6));

		// then
		assertThat(frame.getOptionalScore()).isEqualTo(Optional.of(9));
	}

	@Test
	void 스페어는_점수를_한_번_더하고_결과가_나와야_한다() {
		// given
		Frame frame = FinalFrame.newInstance();

		// when
		frame.roll(BowlingPins.of(3));
		frame.roll(BowlingPins.of(7));
		frame.roll(BowlingPins.of(10));

		// then
		assertThat(frame.getOptionalScore()).isEqualTo(Optional.of(20));
	}

	@Test
	void 스트라이크는_점수를_두_번_더하고_결과가_나와야_한다() {
		// given
		Frame frame = FinalFrame.newInstance();

		// when
		frame.roll(BowlingPins.of(10));
		frame.roll(BowlingPins.of(10));
		frame.roll(BowlingPins.of(8));

		// then
		assertThat(frame.getOptionalScore()).isEqualTo(Optional.of(28));
	}

}
