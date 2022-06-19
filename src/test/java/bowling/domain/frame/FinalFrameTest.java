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
    void validateMoveToNextIndex_success() {
        assertThat(frame.validateMoveToNextIndex()).isEqualTo(10);
    }
}
