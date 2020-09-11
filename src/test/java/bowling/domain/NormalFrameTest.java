package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void from() {
        assertThat(NormalFrame.from().isLastFrame()).isFalse();
    }
}
