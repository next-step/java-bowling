package bowling.domain.frame;

import bowling.exception.BowlingFrameException;
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
        assertThat(frames.nextFrameNo()).isEqualTo(1);
    }

    @DisplayName("2번째 프레임")
    @Test
    void frameNo_2() {
        Frames frames = new Frames().next(1).next(2).next(3);
        assertThat(frames.nextFrameNo()).isEqualTo(2);
    }

    @DisplayName("9번째 프레임")
    @Test
    void frameNo_9() {
        Frames frames = new Frames(9).next(1);
        assertThat(frames.nextFrameNo()).isEqualTo(9);
    }

    @DisplayName("10번째 프레임")
    @Test
    void frameNo_10() {
        Frames frames = new Frames(10).next(1);
        assertThat(frames.nextFrameNo()).isEqualTo(10);
    }

    @DisplayName("종료 후 투구시 오류")
    @Test
    void finish() {
        Frames frames = new Frames(10).next(1).next(2);
        assertThat(frames.isFinish()).isTrue();

    }

    @DisplayName("종료 후 투구시 오류")
    @Test
    void error() {
        Frames frames = new Frames(10).next(1).next(2);
        assertThatThrownBy(() -> frames.next(1)).isInstanceOf(BowlingFrameException.class);
    }
}