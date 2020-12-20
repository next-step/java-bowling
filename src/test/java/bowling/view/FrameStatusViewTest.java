package bowling.view;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusViewTest {
    /**
     * 10 / 10 / 10
     * 10 / - / 10
     * 10 / 10 / -
     * 10 / 1 / 9
     * 9 / 1 / 10
     * 9 / 1 / 9
     * 9 / 1 / -
     * 1 / 1
     */

    @Nested
    class NormalFrameStatusTest {
        @Test
        void strike() {
            assertThat(new FrameStatusView(10, null, null).apply()).isEqualTo("X");
        }

        @Test
        void spare() {
            assertThat(new FrameStatusView(2, 8, null).apply()).isEqualTo("2|/");
        }

        @Test
        void miss() {
            assertThat(new FrameStatusView(4, 2, null).apply()).isEqualTo("4|2");
        }

        @Test
        void gutter() {
            assertThat(new FrameStatusView(4, 0, null).apply()).isEqualTo("4|-");
        }

        @Test
        void playing() {
            assertThat(new FrameStatusView(4, null, null).apply()).isEqualTo("4");
        }
    }

    @Test
    void doubleThrow() {
        assertThat(new FrameStatusView(10, 10, 10).apply()).isEqualTo("X|X|X");
        assertThat(new FrameStatusView(10, 10, 9).apply()).isEqualTo("X|X|9");
        assertThat(new FrameStatusView(10, 10, 0).apply()).isEqualTo("X|X|-");
    }

    @Test
    void strikeAndSpare() {
        assertThat(new FrameStatusView(10, 9, 1).apply()).isEqualTo("X|9|/");
    }

    @Test
    void strikeAndMiss() {
        assertThat(new FrameStatusView(10, 9, 0).apply()).isEqualTo("X|9|-");
    }

    @Test
    void spareAndStrike() {
        assertThat(new FrameStatusView(9, 1, 10).apply()).isEqualTo("9|/|X");
        assertThat(new FrameStatusView(9, 1, 9).apply()).isEqualTo("9|/|9");
        assertThat(new FrameStatusView(9, 1, 0).apply()).isEqualTo("9|/|-");
    }

    @Test
    void miss() {
        assertThat(new FrameStatusView(8, 1, null).apply()).isEqualTo("8|1");
    }

    @Test
    void playing() {
        assertThat(new FrameStatusView(null, null, null).apply()).isEqualTo("");
        assertThat(new FrameStatusView(10, null, null).apply()).isEqualTo("X");
        assertThat(new FrameStatusView(8, 2, null).apply()).isEqualTo("8|/");
        assertThat(new FrameStatusView(8, null, null).apply()).isEqualTo("8");
        assertThat(new FrameStatusView(8, 0, null).apply()).isEqualTo("8|-");
        assertThat(new FrameStatusView(10, 10, null).apply()).isEqualTo("X|X");
        assertThat(new FrameStatusView(10, 8, null).apply()).isEqualTo("X|8");
        assertThat(new FrameStatusView(10, 0, null).apply()).isEqualTo("X|-");
    }

}
