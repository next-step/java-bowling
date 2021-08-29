package bowling.domain.pitch;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.KnockedPins;
import bowling.domain.exception.InvalidPinCountException;

@DisplayName("투구 - 일반")
class NormalPitchTest {

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

	static Stream<Arguments> normalInvalid() {
		return Stream.of(
			Arguments.of(new NormalPitch(5), 6)
		);
	}

	@DisplayName("다음 투구 - 유효하지 않은 다음 투구")
	@ParameterizedTest
	@MethodSource("normalInvalid")
	void play_invalidNormal(final NormalPitch normalPitch, final int pinCount) {
		// given

		// when
		Assertions.assertThrows(InvalidPinCountException.class, () -> normalPitch.play(pinCount));

		// then
	}
}
