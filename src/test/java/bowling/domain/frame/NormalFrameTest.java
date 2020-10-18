package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("프레임생성 테스트")
    @Test
    void testCreate() {
        NormalFrame normalFrame = NormalFrame.firstFrame();
        assertThat(normalFrame.getIndex()).isEqualTo(1);
    }


    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void rollTest(int pin) {
        // given
        NormalFrame normalFrame = NormalFrame.firstFrame();
        int hitedPins = pin + pin;

        // when
        normalFrame.roll(pin);
        normalFrame.roll(pin);

        // then
        assertThat(hitedPins).isEqualTo(normalFrame.getTotal());
    }

    @DisplayName("두번 이상 던지기 테스트")
    @Test
    void testRollThree() {
        NormalFrame normalFrame = NormalFrame.firstFrame();
        normalFrame.roll(3);
        normalFrame.roll(2);

        assertThat(normalFrame.canRoll()).isFalse();

        normalFrame.roll(2);
        assertThat(normalFrame.getTotal()).isEqualTo(5);

    }

    @Test
    void testNormalScore() {
        NormalFrame firstFrame = NormalFrame.firstFrame();
        firstFrame.roll(3);
        firstFrame.roll(2);

        assertThat(firstFrame.getTotal()).isEqualTo(5);
        System.out.println(firstFrame.getScore());
    }

    @Test
    void testSpareScore() {
        NormalFrame firstFrame = NormalFrame.firstFrame();
        firstFrame.roll(5);
        firstFrame.roll(5);

        NormalFrame secondFrame = new NormalFrame(2);
        secondFrame.roll(3);

        assertThat(secondFrame.getScore()).isEqualTo(8);
    }

}
