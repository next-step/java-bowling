package frame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 첫번째_프레임만들기() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame(new ArrayList<>());

        assertThat(firstFrame).isEqualTo(new NormalFrame(1, new ArrayList<>()));
    }

    @Test
    void nextNormalFrame() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame(new ArrayList<>());

        assertThat(firstFrame.nextFrame(new ArrayList<>())).isEqualTo(new NormalFrame(2, new ArrayList<>()));
    }
}