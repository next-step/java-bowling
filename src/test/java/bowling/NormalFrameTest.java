package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.exception.FrameException;
import bowling.frame.Frame;
import bowling.frame.NormalFrame;
import bowling.pin.Pins;

public class NormalFrameTest {

	@Test
	public void createFrameTest() {
		assertThatThrownBy(() -> NormalFrame.of(10))
				.isInstanceOf(FrameException.class)
				.hasMessage("일반 프레임의 번호는 9를 넘을 수 없습니다.");
	}

	@ParameterizedTest
	@MethodSource("reflectProvider")
	public void firstReflectTest(Pins pins, boolean finish, String knockingDownPins) {
		Frame frame = NormalFrame.of(1);
		frame.reflect(pins);
		assertThat(frame.finish()).isEqualTo(finish);
		assertThat(frame.getKnockingDownPinsSigns()).containsExactly(knockingDownPins);
	}

	private static Stream<Arguments> reflectProvider() {
		return Stream.of(
				Arguments.arguments(Pins.of(5), false, "5"),
				Arguments.arguments(Pins.of(10), true, "X")
						);
	}

	@ParameterizedTest
	@MethodSource("secondReflectProvider")
	public void secondReflectTest(Pins pins, boolean finish, String[] knockingDownPins) {
		Frame frame = NormalFrame.of(1);
		frame.reflect(Pins.of(5));
		frame.reflect(pins);
		assertThat(frame.finish()).isEqualTo(finish);
		assertThat(frame.getKnockingDownPinsSigns()).containsExactly(knockingDownPins);
	}

	private static Stream<Arguments> secondReflectProvider() {
		return Stream.of(
				Arguments.arguments(Pins.of(5), true, new String[]{"5", "/"}),
				Arguments.arguments(Pins.of(0), true, new String[]{"5", "-"}),
				Arguments.arguments(Pins.of(1), true, new String[]{"5", "1"}),
				Arguments.arguments(Pins.of(2), true, new String[]{"5", "2"}),
				Arguments.arguments(Pins.of(3), true, new String[]{"5", "3"}),
				Arguments.arguments(Pins.of(4), true, new String[]{"5", "4"})
						);
	}
}
