package bowling.domain.pitch;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.KnockedPins;

@DisplayName("투구 - 스페어")
class SparePitchTest {

	static Stream<Arguments> spare() {
		return Stream.of(
			Arguments.of(new NormalPitch(1), 9),
			Arguments.of(new NormalPitch(9), 1)
		);
	}

	@DisplayName("다음 투구 - 결과 스페어")
	@ParameterizedTest
	@MethodSource("spare")
	void play_spare(final NormalPitch normalPitch, final int pinCount) {
		// given

		// when
		final Pitch pitch = normalPitch.play(pinCount);

		// then
		assertThat(pitch).isInstanceOf(SparePitch.class);
		assertThat(pitch.getKnockedPins()).isEqualTo(KnockedPins.of(pinCount));
	}

	static Stream<Arguments> normal() {
		return Stream.of(
			Arguments.of(new NormalPitch(0), 1),
			Arguments.of(new NormalPitch(0), 9),
			Arguments.of(new NormalPitch(1), 0),
			Arguments.of(new NormalPitch(1), 8)
		);
	}

	@DisplayName("다음 투구 - 결과 일반")
	@ParameterizedTest
	@MethodSource("normal")
	void play_normal(final NormalPitch normalPitch, final int pinCount) {
		// given

		// when
		final Pitch pitch = normalPitch.play(pinCount);

		// then
		assertThat(pitch).isInstanceOf(NormalPitch.class);
		assertThat(pitch.getKnockedPins()).isEqualTo(KnockedPins.of(pinCount));
	}
}
