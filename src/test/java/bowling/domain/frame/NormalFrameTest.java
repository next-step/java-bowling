package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void create() {
        Frame frame = new NormalFrame();
        assertThat(frame).isEqualTo(new NormalFrame());
    }

    @DisplayName("첫번째 투구")
    @Test
    void firstBowl() {
        Frame frame = new NormalFrame().next(1);
        assertThat(frame.firstCount()).isEqualTo(1);
    }

    @DisplayName("miss 인 경우 2번째 투구를 한다.")
    @Test
    void miss() {
        Frame frame = new NormalFrame().next(1).next(2);
        assertThat(frame.secondCount()).isEqualTo(2);
    }

    @DisplayName("strike 인 경우 2번째 투구를 하지 않고 다음 프레임으로 넘어간다.")
    @Test
    void strike() {
        Frame frame = new NormalFrame().next(10).next(1);
        assertThat(frame.firstCount()).isEqualTo(1);
    }

    @DisplayName("2번 miss 후 3번째 투구 시 다음 프레임으로 넘어간다.")
    @Test
    void miss_firstBowl() {
        Frame frame = new NormalFrame().next(1).next(2).next(3);
        assertThat(frame.firstCount()).isEqualTo(3);
    }
}