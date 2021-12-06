package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {

    @DisplayName("Test frame when Sequential is None")
    @Test
    void testFrameWhenSequentialIsNone() {
        Frame firstFrame = noneFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(new Frame(Sequential.STRIKE));

        Frame secondFrame = noneFrame()
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(new Frame(Sequential.SPARE));

        Frame thirdFrame = noneFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(new Frame(Sequential.NONE));
    }

    @DisplayName("Test frame when Sequential is Spare")
    @Test
    void testFrameWhenSequentialIsSpare() {
        Frame firstFrame = spareFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(new Frame(Sequential.STRIKE));

        Frame secondFrame = spareFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(new Frame(Sequential.SPARE));

        Frame thirdFrame = spareFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(new Frame(Sequential.NONE));
    }

    @DisplayName("Test scores when Sequential is Strike")
    @Test
    void testFrameWhenSequentialIsSpare() {
        Frame firstFrame = strikeFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(new Frame(Sequential.DOUBLE_STRIKE));

        Frame secondFrame = strikeFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(new Frame(Sequential.SPARE));

        Frame thirdFrame = strikeFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(new Frame(Sequential.NONE));
    }

    @DisplayName("Test scores when Sequential is Double Strike")
    @Test
    void testFrameWhenSequentialIsSpare() {
        Frame firstFrame = doubleStrikeFrame();
        assertThat(firstFrame.pitch(10)).isEqualTo(new Frame(Sequential.DOUBLE_STRIKE));

        Frame secondFrame = doubleStrikeFrame();
        assertThat(secondFrame.pitch(5)).isEqualTo(secondFrame);
        assertThat(secondFrame.pitch(5)).isEqualTo(new Frame(Sequential.SPARE));

        Frame thirdFrame = doubleStrikeFrame();
        assertThat(thirdFrame.pitch(5)).isEqualTo(thirdFrame);
        assertThat(thirdFrame.pitch(3)).isEqualTo(new Frame(Sequential.NONE));
    }

    private Frame noneFrame() {
        return new Frame(Sequential.NONE);
    }

    private Frame spareFrame() {
        return new Frame(Sequential.SPARE;
    }

    private Frame strikeFrame() {
        return new Frame(Sequential.STRIKE);
    }

    private Frame doubleStrikeFrame() {
        return new Frame(Sequential.DOUBLE_STRIKE);
    }
}
