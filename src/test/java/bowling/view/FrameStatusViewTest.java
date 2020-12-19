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
            assertThat(new FrameStatusView(10, null, null).toString()).isEqualTo("X");
        }

        @Test
        void spare() {
            assertThat(new FrameStatusView(2, 8, null).toString()).isEqualTo("2|/");
        }

        @Test
        void miss() {
            assertThat(new FrameStatusView(4, 2, null).toString()).isEqualTo("4|2");
        }

        @Test
        void gutter() {
            assertThat(new FrameStatusView(4, 0, null).toString()).isEqualTo("4|-");
        }

        @Test
        void playing() {
            assertThat(new FrameStatusView(4, null, null).toString()).isEqualTo("4");
        }
    }

    @Test
    void doubleThrow() {
        assertThat(new FrameStatusView(10, 10, 10).toString()).isEqualTo("X|X|X");
        assertThat(new FrameStatusView(10, 10, 9).toString()).isEqualTo("X|X|9");
        assertThat(new FrameStatusView(10, 10, 0).toString()).isEqualTo("X|X|-");
    }

    @Test
    void strikeAndSpare() {
        assertThat(new FrameStatusView(10, 9, 1).toString()).isEqualTo("X|9|/");
    }

    @Test
    void strikeAndMiss() {
        assertThat(new FrameStatusView(10, 9, 0).toString()).isEqualTo("X|9|-");
    }

    @Test
    void spareAndStrike() {
        assertThat(new FrameStatusView(9, 1, 10).toString()).isEqualTo("9|/|X");
        assertThat(new FrameStatusView(9, 1, 9).toString()).isEqualTo("9|/|9");
        assertThat(new FrameStatusView(9, 1, 0).toString()).isEqualTo("9|/|-");
    }

    @Test
    void miss() {
        assertThat(new FrameStatusView(8, 1, null).toString()).isEqualTo("8|1");
    }

    @Test
    void playing() {
        assertThat(new FrameStatusView(null, null, null).toString()).isEqualTo("");
        assertThat(new FrameStatusView(10, null, null).toString()).isEqualTo("X");
        assertThat(new FrameStatusView(8, 2, null).toString()).isEqualTo("8|/");
        assertThat(new FrameStatusView(8, null, null).toString()).isEqualTo("8");
        assertThat(new FrameStatusView(8, 0, null).toString()).isEqualTo("8|-");
        assertThat(new FrameStatusView(10, 10, null).toString()).isEqualTo("X|X");
        assertThat(new FrameStatusView(10, 8, null).toString()).isEqualTo("X|8");
        assertThat(new FrameStatusView(10, 0, null).toString()).isEqualTo("X|-");
    }

}
