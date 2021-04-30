package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FrameStatusTest {

    static Stream<Arguments> nullPinSource() {
        return Stream.of(
                arguments(null, null),
                arguments(new Pin(9), null)
        );
    }

    static Stream<Arguments> nullThreePinSource() {
        return Stream.of(
                arguments(null, null, null),
                arguments(new Pin(9), null, null),
                arguments(TestFixture.STRIKE_PIN, TestFixture.STRIKE_PIN, null),
                arguments(new Pin(9), new Pin(1), null)
        );
    }

    @ParameterizedTest
    @CsvSource({"10,0,STRIKE", "9,1,SPARE", "3,4,NORMAL", "0,0,MISS"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void of(int firstPinCount, int secondPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);

        // when
        final FrameStatus frameStatus = FrameStatus.of(firstPin, secondPin);

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }

    @ParameterizedTest
    @CsvSource({"10,10,10,NORMAL", "10,0,10,NORMAL", "0,10,10,NORMAL", "9,1,3,NORMAL", "3,4,0,NORMAL", "0,0,0,NORMAL"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void of(int firstPinCount, int secondPinCount, int thirdPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pin thirdPin = new Pin(thirdPinCount);

        // when
        final FrameStatus frameStatus = FrameStatus.of(firstPin, secondPin, thirdPin);

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }

    static Stream<Arguments> pinsAndStatusSource() {
        return Stream.of(
                arguments(Pins.from(Lists.list()), FrameStatus.NOT_ENDED),
                arguments(Pins.from(Lists.list(TestFixture.STRIKE_PIN)), FrameStatus.STRIKE),
                arguments(Pins.from(Lists.list(new Pin(2))), FrameStatus.NOT_ENDED),
                arguments(Pins.from(Lists.list(new Pin(2), new Pin(8))), FrameStatus.SPARE),
                arguments(Pins.from(Lists.list(new Pin(1), new Pin(8))), FrameStatus.NORMAL),
                arguments(Pins.from(Lists.list(TestFixture.GUTTER_PIN, TestFixture.GUTTER_PIN)), FrameStatus.MISS)
        );
    }

    @ParameterizedTest
    @MethodSource("nullPinSource")
    @DisplayName("두 개의 핀을 가지고 경기가 끝나지 않은 경우 NOT_ENDED이다.")
    void ofNotEnded(Pin firstPin, Pin secondPin) {
        // given
        // when
        final FrameStatus frameStatus = FrameStatus.of(firstPin, secondPin);

        // then
        assertThat(frameStatus).isEqualTo(FrameStatus.NOT_ENDED);
    }

    @ParameterizedTest
    @MethodSource("nullThreePinSource")
    @DisplayName("세 개의 핀을 가지고 경기가 끝나지 않은 경우 NOT_ENDED이다.")
    void ofFinalNotEnded(Pin firstPin, Pin secondPin, Pin thirdPin) {
        // given
        // when
        final FrameStatus frameStatus = FrameStatus.of(firstPin, secondPin, thirdPin);

        // then
        assertThat(frameStatus).isEqualTo(FrameStatus.NOT_ENDED);
    }

    @ParameterizedTest
    @MethodSource("pinsAndStatusSource")
    @DisplayName("Pins를 가지고 두 개의 Pin에 대한 Status를 반환할 수 있다.")
    void ofList(Pins pins, FrameStatus expectedStatus) {
        // given
        // when
        final FrameStatus frameStatus = FrameStatus.of(pins);

        // then
        assertThat(frameStatus).isEqualTo(expectedStatus);
    }
}
