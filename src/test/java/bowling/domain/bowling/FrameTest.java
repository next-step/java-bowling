package bowling.domain.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.domain.Pin.Pin;
import bowling.domain.Pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    @DisplayName("첫번째 프레임을 정상적으로 생성할 수 있다.")
    void createFrameTest() {

        // given
        Pin pin = Pin.of(10);
        Pins pins = Pins.ofFirst(pin);

        // when
        Frame frame = Frame.ofStart(pins);

        // then
        assertThat(frame).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("현재 프레임으로 다음 프레임을 생성할 수 있다.")
    void createNextFrameTest() {

        // given
        Pin pin = Pin.of(10);
        Pins pins = Pins.ofFirst(pin);
        Frame first = Frame.ofStart(pins);

        // when
        Frame result = first.ofNext(pins);

        // then
        assertAll(
            () -> assertThat(result).isInstanceOf(Frame.class),
            () -> assertThat(result.frameStage()).isEqualTo(2)
        );

    }
}