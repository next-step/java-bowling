package bowling.domain.pitch;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.Pins;

@DisplayName("투구 - 스트라이크")
class StrikePitchTest {

	static Stream<Arguments> strike() {
		return Stream.of(
			Arguments.of(new StrikePitch(), 10)
		);
	}

	@DisplayName("다음 투구 - 결과 스트라이크")
	@ParameterizedTest
	@MethodSource("strike")
	void play_strike(final StrikePitch strikePitch, final int pinCount) {
		// given

		// when
		final Pitch pitch = strikePitch.play(pinCount);

		// then
		assertThat(pitch).isInstanceOf(StrikePitch.class);
	}

	static Stream<Arguments> normal() {
		return Stream.of(
			Arguments.of(new StrikePitch(), 1),
			Arguments.of(new StrikePitch(), 9)
		);
	}

	@DisplayName("다음 투구 - 결과 일반")
	@ParameterizedTest
	@MethodSource("normal")
	void play_spare(final StrikePitch strikePitch, final int pinCount) {
		// given

		// when
		final Pitch pitch = strikePitch.play(pinCount);

		// then
		assertThat(pitch).isInstanceOf(NormalPitch.class);
		assertThat(pitch.getPins()).isEqualTo(Pins.of(pinCount));
	}
}
