package bowling.domain.Frame;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void create() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(normalFrame).isEqualTo(new NormalFrame());
    }

    @DisplayName("miss 인 경우 현재 프레임에서 투구한다.")
    @Test
    void frameNo_miss() {
        NormalFrame normalFrame = new NormalFrame().next(1);
        assertThat(normalFrame.getFrameNo()).isEqualTo(1);
    }

    @DisplayName("strike 인 경우 다음 프레임에서 투구한다.")
    @Test
    void frameNo_strike() {
        NormalFrame normalFrame = new NormalFrame().next(10);
        assertThat(normalFrame.getFrameNo()).isEqualTo(2);
    }
}