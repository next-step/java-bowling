package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private FinalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new FinalFrame(10);
    }

    @Test
    void score_success() {
        int first = frame.score();
        assertThat(frame.scores().size()).isEqualTo(1);

        if (first == 10) {
            assertThat(frame.score()).isBetween(1, 11);
        } else {
            assertThat(frame.score()).isBetween(1, 11 - first);
        }

        assertThat(frame.scores().size()).isEqualTo(2);
    }

    @Test
    void validateMoveToNextIndex_success() {
        assertThat(frame.validateMoveToNextIndex()).isEqualTo(10);
    }
}
