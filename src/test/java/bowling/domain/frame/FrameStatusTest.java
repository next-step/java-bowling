package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameStatusTest {

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
}
