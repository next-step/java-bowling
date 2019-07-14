package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameCounterTest {

    @Test
    void addFrameCountTest() {
        assertThat(FrameCounter.addFrameCount()).isEqualTo(1);
    }

    @Test
    void isFinalFrameTest() {
        assertThat(FrameCounter.isFinalFrame()).isFalse();
    }
}
