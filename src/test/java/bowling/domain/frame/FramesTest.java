package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("정상 생성")
    void testCrateFrames() {
        // given & when
        Frames frames = new Frames();
        // then
        assertThat(frames.isComplete()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임이 완료일 경우 완료 확인")
    void testFrameIsComplete() {
        // given
        Frames frames = new Frames();
        frames.endCurrentFrame();
        // when
        for (int i = 0; i < 9; i++) {
            frames.nextFrame();
            frames.endCurrentFrame();
        }
        // then
        assertThat(frames.isComplete()).isTrue();
    }
}
