package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.DOWN_ALL;
import static bowling.model.Pins.DOWN_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("첫번째 공이 일부 쓰러트렸을 시 현재 프레임을 다시 반환한다")
    @Test
    void firstBowl_hit_returnCurrentFrame() {
        // given
        Pins pins = Pins.valueOf(1);

        // when
        Frame beforeFrame = NormalFrame.ofFirst();
        Frame afterFrame = beforeFrame.bowling(pins);

        // then
        assertThat(beforeFrame).isEqualTo(afterFrame);
    }

    @DisplayName("첫번째 공이 gutter 시 현 프레임을 반환한다")
    @Test
    void firstBowl_gutter_returnCurrentFrame() {
        // given
        Pins pins = DOWN_ZERO;

        // when
        Frame beforeFrame = NormalFrame.ofFirst();
        Frame afterFrame = beforeFrame.bowling(pins);

        // then
        assertThat(afterFrame).isEqualTo(beforeFrame);
        assertThat(afterFrame.getIndex()).isEqualTo(1);
    }

    @DisplayName("첫번째 공이 Strike 시 다음 프레임으로 이동한다")
    @Test
    void firstBowl_strike_returnNextFrame() {
        // given
        Pins pins = DOWN_ALL;

        // when
        Frame beforeFrame = NormalFrame.ofFirst();
        Frame afterFrame = beforeFrame.bowling(pins);

        // then
        assertThat(afterFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("두번째 공을 던졌을 시 다음 프레임으로 이동한다")
    @Test
    void bowl_secondBowl_returnNextFrame() {
        // given
        Pins firstBowl = DOWN_ZERO;
        Pins secondBowl = DOWN_ZERO;

        // when
        Frame beforeFrame = NormalFrame.ofFirst();
        Frame afterFrame = beforeFrame
                .bowling(firstBowl)
                .bowling(secondBowl);

        // then
        assertThat(afterFrame.getIndex()).isEqualTo(2);
    }
}
