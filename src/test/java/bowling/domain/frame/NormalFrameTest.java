package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void rollTest(int pin) {
        // given
        NormalFrame normalFrame = NormalFrame.generateNormalFrame();
        int hitedPins = new Pin(pin).getPin() + new Pin(pin).getPin();

        // when
        normalFrame.roll(new Pin(pin));
        normalFrame.roll(new Pin(pin));

        // then
        assertThat(hitedPins).isEqualTo(normalFrame.getTotal());
    }

    @DisplayName("두번 이상 던지기 테스트")
    @Test
    void testRollThree() {
        NormalFrame normalFrame = NormalFrame.generateNormalFrame();
        normalFrame.roll(new Pin(3));
        normalFrame.roll(new Pin(2));

        assertThat(normalFrame.canRoll()).isFalse();

        normalFrame.roll(new Pin(2));
        assertThat(normalFrame.getTotal()).isEqualTo(5);

    }

    @Test
    void testNormalScore() {
        NormalFrame firstFrame = NormalFrame.generateNormalFrame();

        firstFrame.roll(new Pin(3));
        firstFrame.roll(new Pin(2));

        assertThat(firstFrame.getTotal()).isEqualTo(5);
    }

}
