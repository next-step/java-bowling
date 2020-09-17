package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NormalFrameTest {

    @Test
    void frameIndexExceptionTest() {
        int frameIndex = 10;
        assertThatThrownBy(() -> {
                new NormalFrame(frameIndex);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rollingEndTest() {
        int frameIndex = 5;

        NormalFrame normalFrame = new NormalFrame(frameIndex);
        normalFrame.bowl(new Pin(2));
        normalFrame.bowl(new Pin(5));

        assertTrue(normalFrame.rollingEnd());
    }

    @Test
    void rollingEndByStrikeTest() {
        int frameIndex = 5;

        NormalFrame normalFrame = new NormalFrame(frameIndex);
        normalFrame.bowl(new Pin(10));

        assertTrue(normalFrame.rollingEnd());
    }

    @Test
    void rollingEndBySpareTest() {
        int frameIndex = 5;

        NormalFrame normalFrame = new NormalFrame(frameIndex);
        normalFrame.bowl(new Pin(2));
        normalFrame.bowl(new Pin(8));

        assertTrue(normalFrame.rollingEnd());
    }

    @Test
    void rollingNotEndTest() {
        int frameIndex = 5;

        NormalFrame normalFrame = new NormalFrame(frameIndex);
        normalFrame.bowl(new Pin(5));

        assertFalse(normalFrame.rollingEnd());
    }

    @Test
    void frameIndexTest() {
        int frameIndex = 5;
        NormalFrame normalFrame = new NormalFrame(frameIndex);

        assertThat(normalFrame.index()).isEqualTo(String.valueOf(frameIndex));
    }
}
