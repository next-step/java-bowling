package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp(){
        frames = Frames.init();
    }

    @DisplayName("한 프레임의 투구가 끝나면 다음 프레임이 생성된다.")
    @Test
    void pitch() {
        Frame testFrame = new NormalFrame(1, Arrays.asList(10));
        assertThat(frames.pitch(10)).isEqualTo(testFrame);
        assertThat(frames.pitch(2)).isEqualTo(new NormalFrame(2, Arrays.asList(2)));
    }

}