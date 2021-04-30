package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FrameStatusTest {

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
