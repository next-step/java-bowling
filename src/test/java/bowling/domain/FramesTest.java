package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    private static Frames frames;

    @BeforeEach
    void beforeEach() {
        frames = Frames.init();
    }

    @DisplayName("Frames 초기화")
    @Test
    void init() {
        assertThat(frames.getLastFrameIndex()).isEqualTo(FrameIndex.MIN);
        assertThat(frames.getFrames().size()).isEqualTo(FrameIndex.MIN);
    }

    @DisplayName("프레임이 종료 상태가 아니면 새로운 프레임이 추가되지 않는다")
    @Test
    void bowlWithoutAddFrame() {
        // when
        frames.bowl(Pin.of(5));
        // then
        assertThat(frames.getFrames()).hasSize(FrameIndex.MIN);
    }

    @DisplayName("프레임이 종료 상태로 변경되면 새로운 프레임이 추가된다")
    @Test
    void bowlWithAddFrame() {
        // when
        frames.bowl(Pin.of(10));

        // then
        assertThat(frames.getFrames()).hasSize(FrameIndex.MIN + 1);
    }

    @DisplayName("프레임이면서 종료 상태가 아니면 다음이 존재한다")
    @Test
    void hasNext() {
        assertThat(frames.hasNext()).isTrue();
    }
}
