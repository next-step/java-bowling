package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("프레임을 추가하고 새로운 참조를 반환")
    @Test
    public void addFrame_success() throws Exception {
        //given
        Frames frames = new Frames(new LinkedList<>(Arrays.asList(new NormalFrame(), new NormalFrame())));

        //when
        frames = frames.addFrame(new FinalFrame());

        //then
        assertThat(frames.getFrames().size()).isEqualTo(3);
    }
}
