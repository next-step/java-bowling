package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {
    @DisplayName("투구 회차가 달라도 프레임은 같아야한다.")
    @Test
    void create() {
        Frame frame = new Frame();
        Frame first = frame.next(1);
        Frame second = frame.next(9);
        assertThat(frame).isEqualTo(first);
        assertThat(first).isNotEqualTo(second);
    }

    @DisplayName("miss 인 경우 현재 프레임에서 투구한다.")
    @Test
    void frameNo_miss() {
        Frame frame = new Frame().next(1);
        assertThat(frame.getFrameNo()).isEqualTo(1);
    }

    @DisplayName("strike 인 경우 다음 프레임에서 투구한다.")
    @Test
    void frameNo_strike() {
        Frame frame = new Frame().next(10);
        assertThat(frame.getFrameNo()).isEqualTo(2);
    }
}