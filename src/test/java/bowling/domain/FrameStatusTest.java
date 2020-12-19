package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    @Test
    void strike() {
        assertThat(FrameStatus.strike().toString()).isEqualTo("X");
    }

    @Test
    void spare() {
        assertThat(FrameStatus.spare(2).toString()).isEqualTo("2|/");
    }

    @Test
    void miss() {
        assertThat(FrameStatus.miss(4, 2).toString()).isEqualTo("4|2");
    }

    @Test
    void gutter() {
        assertThat(new FrameStatus(4, 0).toString()).isEqualTo("4|-");
    }

}
