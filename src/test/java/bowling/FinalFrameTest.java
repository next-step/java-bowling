package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.frame.FinalFrame;
import bowling.frame.Frame;
import bowling.pin.Pins;

public class FinalFrameTest {

	@ParameterizedTest
	@MethodSource("firstReflectProvider")
	public void firstReflectTest(Pins firstPins, boolean finish, String knockingDownPins) {
		Frame frame = FinalFrame.newInstance();
		frame.reflect(firstPins);
		assertThat(frame.finish()).isEqualTo(finish);
		assertThat(frame.getKnockingDownPinsSigns()).containsExactly(knockingDownPins);
	}

	private static Stream<Arguments> firstReflectProvider() {
		return Stream.of(
				Arguments.arguments(Pins.of(5), false, "5"),
				Arguments.arguments(Pins.of(10), false, "X"),
				Arguments.arguments(Pins.of(7), false, "7")
						);
	}

	@ParameterizedTest
	@MethodSource("secondReflectProvider")
	public void secondReflectTest(Pins firstPins, Pins secondPins, boolean finish, String[] knockingDownPins) {
		Frame frame = FinalFrame.newInstance();
		frame.reflect(firstPins);
		frame.reflect(secondPins);
		assertThat(frame.finish()).isEqualTo(finish);
		assertThat(frame.getKnockingDownPinsSigns()).containsExactly(knockingDownPins);
	}

	private static Stream<Arguments> secondReflectProvider() {
		return Stream.of(
				Arguments.arguments(Pins.of(5), Pins.of(5), false, new String[]{"5", "/"}),
				Arguments.arguments(Pins.of(10), Pins.of(0), false, new String[]{"X", "10"}),
				Arguments.arguments(Pins.of(5), Pins.of(2), true, new String[]{"5", "2"})
						);
	}

	@ParameterizedTest
	@MethodSource("thirdReflectProvider")
	public void thirdReflectTest(Pins firstPins, Pins secondPins, Pins thirdPins, boolean finish, String[] knockingDownPins) {
		Frame frame = FinalFrame.newInstance();
		frame.reflect(firstPins);
		frame.reflect(secondPins);
		frame.reflect(thirdPins);
		assertThat(frame.finish()).isEqualTo(finish);
		assertThat(frame.getKnockingDownPinsSigns()).containsExactly(knockingDownPins);
	}

	private static Stream<Arguments> thirdReflectProvider() {
		return Stream.of(
				Arguments.arguments(Pins.of(7), Pins.of(3), Pins.of(10), true, new String[]{"7", "/", "X"}),
				Arguments.arguments(Pins.of(10), Pins.of(10), Pins.of(10), true, new String[]{"X", "X", "X"}),
				Arguments.arguments(Pins.of(5), Pins.of(5), Pins.of(10), true, new String[]{"5", "/", "X"})
						);
	}
}
