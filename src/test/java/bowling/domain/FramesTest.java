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
        assertThat(frames.getLastFrameIndex()).isEqualTo(Index.MIN_INDEX);
        assertThat(frames.getFrames().size()).isEqualTo(Index.MIN_INDEX);
    }

    @DisplayName("프레임이 진행 상태이면 새로운 프레임이 추가되지 않는다")
    @Test
    void bowlWithoutAddFrame() {
        // given
        Pin pins = Pin.of(5);

        // when
        frames.bowl(pins);

        // then
        assertThat(frames.getFrames()).hasSize(1);
    }

    @DisplayName("프레임이 종료상태로 변경되면 새로운 프레임이 추가된다")
    @Test
    void bowlWithAddFrame() {
        // when
        frames.bowl(Pin.of(10));

        // then
        assertThat(frames.getFrames()).hasSize(2);
    }

    @DisplayName("10프레임이면서 종료상태가 아니면 다음이 존재한다")
    @Test
    void hasNext() {
        assertThat(frames.hasNext()).isTrue();
    }
}
