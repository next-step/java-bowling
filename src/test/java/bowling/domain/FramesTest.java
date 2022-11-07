package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("pitch를 하지 않았기에 frame 종료가 아니다.")
    @Test
    void frame_end(){

        final Frames frames = Frames.init();
        frames.addFrame();

        assertThat(frames.end()).isTrue();
    }
}