package bowling.domain.Frame;

import bowling.domain.frame.Frames;
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

    @DisplayName("1번째 프레임")
    @Test
    void frameNo_1() {
        Frames frames = new Frames().next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(1);
    }

    @DisplayName("9번째 프레임")
    @Test
    void frameNo_9() {
        Frames frames = new Frames(9).next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(9);
    }

    @DisplayName("10번째 프레임")
    @Test
    void frameNo_10() {
        Frames frames = new Frames(10).next(10);
        assertThat(frames.currentFrameNo()).isEqualTo(10);
    }

    @DisplayName("종료 후 투구시 오류")
    @Test
    void end() {
        Frames frames = new Frames(10).next(1).next(1);
        assertThatThrownBy(()->frames.next(1)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void all() {
        Frames frames = new Frames().next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(10).next(1).next(1);
        assertThat(frames.currentFrameNo()).isEqualTo(11);
    }
}