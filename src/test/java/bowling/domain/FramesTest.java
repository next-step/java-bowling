package bowling.domain;

import aTest.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Fames 클래스 테스트")
class FramesTest {

    @Test
    void create() {
        Frames frames = new Frames(new NormalFrame());
        assertThat(frames.getCurrentFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void add() {
        Frames frames = new Frames(new NormalFrame());

        frames.add(new NormalFrame());

        assertThat(frames.getFrames()).hasSize(2);
    }
}
