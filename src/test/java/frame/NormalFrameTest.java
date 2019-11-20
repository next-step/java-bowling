package frame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 첫번째_프레임만들기() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame();

        assertThat(firstFrame).isEqualTo(new NormalFrame(1, new ArrayList<>()));
    }

    @Test
    void nextNormalFrame() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame();

        assertThat(firstFrame.nextFrame()).isEqualTo(new NormalFrame(2, new ArrayList<>()));
    }

    @Test
    void isFull_일반프레임_두번던지거나_스트라이크면넘어가기() {
        NormalFrame normalFrame = NormalFrame.firstNormalFrame();
        assertThat(normalFrame.isFull()).isFalse();

        normalFrame.bowling(1);
        assertThat(normalFrame.isFull()).isFalse();

        normalFrame.bowling(1);
        assertThat(normalFrame.isFull()).isTrue();

        NormalFrame strikeFrame = NormalFrame.firstNormalFrame();
        strikeFrame.bowling(10);
        assertThat(strikeFrame.isFull()).isTrue();
    }
}