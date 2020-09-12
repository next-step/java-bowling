package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.exception.PitchingStateException;
import bowling.pin.Pins;
import bowling.pitching.PitchingState;

public class PitchingStateTest {

	@ParameterizedTest
	@MethodSource("beforeProvider")
	public void beforeTest(List<Pins> pins, PitchingState expect) {
		PitchingState before = PitchingState.READY;
		PitchingState result = before.reflect(pins);
		assertThat(result).isEqualTo(expect);
	}

	private static Stream<Arguments> beforeProvider() {
		return Stream.of(
				Arguments.arguments(Arrays.asList(Pins.of(1)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(2)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(3)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(4)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(5)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(6)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(7)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(8)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(9)), PitchingState.ON_GOING),
				Arguments.arguments(Arrays.asList(Pins.of(10)), PitchingState.STRIKE)
						);
	}

	@ParameterizedTest
	@MethodSource("onGoingProvider")
	public void onGoingTest(List<Pins> pins, PitchingState expect) {
		PitchingState onGoing = PitchingState.ON_GOING;
		PitchingState result = onGoing.reflect(pins);
		assertThat(result).isEqualTo(expect);
	}

	private static Stream<Arguments> onGoingProvider() {
		return Stream.of(
				Arguments.arguments(Arrays.asList(Pins.of(1), Pins.of(1)), PitchingState.MISS),
				Arguments.arguments(Arrays.asList(Pins.of(2), Pins.of(8)), PitchingState.SPARE),
				Arguments.arguments(Arrays.asList(Pins.of(3), Pins.of(2)), PitchingState.MISS),
				Arguments.arguments(Arrays.asList(Pins.of(4), Pins.of(5)), PitchingState.MISS),
				Arguments.arguments(Arrays.asList(Pins.of(5), Pins.of(3)), PitchingState.MISS),
				Arguments.arguments(Arrays.asList(Pins.of(6), Pins.of(4)), PitchingState.SPARE),
				Arguments.arguments(Arrays.asList(Pins.of(7), Pins.of(3)), PitchingState.SPARE),
				Arguments.arguments(Arrays.asList(Pins.of(8), Pins.of(0)), PitchingState.GUTTER),
				Arguments.arguments(Arrays.asList(Pins.of(9), Pins.of(1)), PitchingState.SPARE)
						);
	}

	@ParameterizedTest
	@MethodSource("pitchingStateExceptionProvider")
	public void pitchingStateExceptionTest(PitchingState pitchingState, String message) {
		List<Pins> pins = Arrays.asList(Pins.of(4));
		assertThatThrownBy(() -> {
			pitchingState.reflect(pins);
		}).isInstanceOf(PitchingStateException.class)
		  .hasMessage(message);
	}

	private static Stream<Arguments> pitchingStateExceptionProvider() {
		return Stream.of(
				Arguments.arguments(PitchingState.GUTTER, "해당 프레임에서는 더 던질 수 없습니다."),
				Arguments.arguments(PitchingState.MISS, "해당 프레임에서는 더 던질 수 없습니다.")
						);
	}

	@ParameterizedTest
	@MethodSource("canMoveNextFrameProvider")
	public void canMoveNextFrameTest(PitchingState pitchingState, boolean expect1) {
		assertThat(pitchingState.canMoveNextFrame()).isEqualTo(expect1);
	}

	private static Stream<Arguments> canMoveNextFrameProvider() {
		return Stream.of(
				Arguments.arguments(PitchingState.GUTTER, true),
				Arguments.arguments(PitchingState.STRIKE, true),
				Arguments.arguments(PitchingState.SPARE, true),
				Arguments.arguments(PitchingState.MISS, true),
				Arguments.arguments(PitchingState.READY, false),
				Arguments.arguments(PitchingState.ON_GOING, false)
						);
	}
}
