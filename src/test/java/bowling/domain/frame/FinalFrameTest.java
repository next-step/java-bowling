package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @DisplayName("스트라이크일 경우 테스트")
    @Test
    void rollStrikeTest() {
        // given
        FinalFrame finalFrame = new FinalFrame();

        // when
        finalFrame.roll(10);
        finalFrame.roll(3);
        finalFrame.roll(3);

        // then
        assertThat(finalFrame.getTotal()).isEqualTo(16);
    }

    @DisplayName("스페어인 경우 테스트")
    @Test
    void rollSpareTest() {
        // given
        FinalFrame finalFrame = new FinalFrame();

        // when
        finalFrame.roll(7);
        finalFrame.roll(3);
        finalFrame.roll(3);

        // then
        assertThat(finalFrame.getTotal()).isEqualTo(13);
    }

    @DisplayName("두번 이상 던지기 테스트")
    @Test
    void testRollThree() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.roll(3);
        finalFrame.roll(2);

        assertThat(finalFrame.canRoll()).isFalse();

        finalFrame.roll(2);
        assertThat(finalFrame.getTotal()).isEqualTo(5);

    }

    @DisplayName("스페어 스트라이크 없는 상태에서 핀의 갯수체크")
    @Test
    void testdPinsWithoutStrikeOrSpare() {
        FinalFrame finalFrame = new FinalFrame();

        assertThatThrownBy(() -> {
            finalFrame.roll(5);
            finalFrame.roll(6);

        }).isInstanceOf(IllegalArgumentException.class);
    }
}
