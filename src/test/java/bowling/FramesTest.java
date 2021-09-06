package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @Test
    void create() {
        Frames frames = new Frames();
        assertThat(frames).isEqualTo(new Frames());
    }

    @DisplayName("miss 인 경우 두번째 투구를 던진다.")
    @Test
    void frameNo_miss() {
        Frames frames = new Frames().next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(1);
    }

    @DisplayName("strike 인 경우 다음 프레임으로 넘어간다.")
    @Test
    void frameNo_strike() {
        Frames frames = new Frames().next(10);
        assertThat(frames.currentFrameNo()).isEqualTo(2);
    }

    @DisplayName("10번째 프레임은 spare 인 경우 3번까지 투구가 가능하다.")
    @Test
    void finalFrame_spare() {
        Frames frames = new Frames(10).next(1).next(9);
        assertThat(frames.currentFrameNo()).isEqualTo(10);
    }

    @DisplayName("10번째 프레임은 strike 인 경우 3번까지 투구가 가능하다.")
    @Test
    void finalFrame_strike() {
        Frames frames = new Frames(10).next(10).next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(10);
    }

    @DisplayName("10번째 프레임은 두번다 miss 인 경우 2번까지 투구가 가능하다.")
    @Test
    void finalFrame_miss() {
        Frames frames = new Frames(10).next(1).next(8);
        assertThat(frames.currentFrameNo()).isEqualTo(11);
    }

    @DisplayName("10번째 프레임에서 두번다 miss 인 경우 3번째 투구 시도시 에러 발생")
    @Test
    void finalFrame_miss_error() {
        Frames frames = new Frames(10).next(1).next(8);
        assertThatThrownBy(() -> frames.next(1)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("10번째 프레임에서 strike 인 경우 4번째 투구 시도시 에러 발생")
    @Test
    void finalFrame_strike_error() {
        Frames frames = new Frames(10).next(10).next(8).next(2);
        assertThatThrownBy(() -> frames.next(1)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void all() {
        Frames frames = new Frames().next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(1).next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(11);
    }
}