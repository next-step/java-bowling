package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest {
    @Test
    void from() {
        assertThat(LastFrame.from().isLastFrame()).isTrue();
    }
}
