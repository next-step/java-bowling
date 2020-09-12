package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.exception.PinsException;
import bowling.pin.Pins;

public class PinsTest {

	@ParameterizedTest
	@MethodSource("knockingDownPinsProvider")
	public void knockingDownPins(int pinCount, int expectedPinCount) {
		Pins pins = Pins.of(pinCount);
		assertThat(pins.getKnockingDownPins()).isEqualTo(expectedPinCount);
	}

	@ParameterizedTest
	@MethodSource("allKnockingDownProvider")
	public void allKnockingDown(int pinCount, boolean allKnockingDown) {
		Pins pins = Pins.of(pinCount);
		assertThat(pins.isAllKnockingDown()).isEqualTo(allKnockingDown);
	}

	@ParameterizedTest
	@MethodSource("notKnockedDownAtAllProvider")
	public void notKnockedDownAtAll(int pinCount, boolean notKnockedDownAtAll) {
		Pins pins = Pins.of(pinCount);
		assertThat(pins.isNotKnockedDownAtAll()).isEqualTo(notKnockedDownAtAll);
	}

	@Test
	public void addKnockingDownPinsTest() {
		Pins pins1 = Pins.of(5);
		Pins pins2 = Pins.of(5);
		assertThat(pins2.addKnockingDownPins(pins1)).isEqualTo(10);
	}

	@Test
	public void addKnockingDownPinsExceptionTest() {
		Pins pins1 = Pins.of(7);
		Pins pins2 = Pins.of(4);

		assertThatThrownBy(() -> {
			pins2.addKnockingDownPins(pins1);
		}).isInstanceOf(PinsException.class)
		  .hasMessage("넘어진 핀의 총 합이 10개를 넘을 수 없습니다.");
	}

	private static Stream<Arguments> knockingDownPinsProvider() {
		return Stream.of(
				Arguments.arguments(5, 5),
				Arguments.arguments(6, 6),
				Arguments.arguments(0, 0),
				Arguments.arguments(10, 10),
				Arguments.arguments(1, 1)
						);
	}

	private static Stream<Arguments> allKnockingDownProvider() {
		return Stream.of(
				Arguments.arguments(5, false),
				Arguments.arguments(6, false),
				Arguments.arguments(0, false),
				Arguments.arguments(10, true),
				Arguments.arguments(1, false)
						);
	}

	private static Stream<Arguments> notKnockedDownAtAllProvider() {
		return Stream.of(
				Arguments.arguments(5, false),
				Arguments.arguments(6, false),
				Arguments.arguments(0, true),
				Arguments.arguments(10, false),
				Arguments.arguments(1, false)
						);
	}
}
