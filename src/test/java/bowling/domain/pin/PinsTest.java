package bowling.domain.pin;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PinsTest {

    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void addTest(int pin) {
        // given
        Pins pins = new Pins();
        Frame frame = NormalFrame.firstFrame();
        // when
        pins.addPins(frame, pin);

        // then
        assertThat(pins.getTotalPins()).isEqualTo(pin);
    }

    @DisplayName("스트라이크 이후 던지기 테스트")
    @Test
    void testRollAfterStrike() {

        assertThatThrownBy(() -> {
            Pins pins = new Pins();
            Frame frame = NormalFrame.firstFrame();
            // when
            pins.addPins(frame, 10);
            pins.addPins(frame, 5);

        }).isInstanceOf(IllegalArgumentException.class);
    }

}

