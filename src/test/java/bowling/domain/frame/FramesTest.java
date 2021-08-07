package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("frmae 10개가 포함된 frames를 만들 수 있다")
    @Test
    void should_make_frames_in_10_frame() {
        //arrange, act, assert
        assertThat(Frames.of()).isInstanceOf(Frames.class);
    }

}