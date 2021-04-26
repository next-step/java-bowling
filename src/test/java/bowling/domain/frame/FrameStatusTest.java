package bowling.domain.frame;

import bowling.domain.pin.Pin;
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

    @ParameterizedTest
    @CsvSource({"10,0,STRIKE", "9,1,SPARE", "3,4,MISS", "0,0,GUTTER"})
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
    @MethodSource("nullPinSource")
    @DisplayName("경기가 끝나지 않은 경우 None이다.")
    void ofNone(Pin firstPin, Pin secondPin) {
        // given
        // when
        final FrameStatus frameStatus = FrameStatus.of(firstPin, secondPin);

        // then
        assertThat(frameStatus).isEqualTo(FrameStatus.NONE);
    }
}
