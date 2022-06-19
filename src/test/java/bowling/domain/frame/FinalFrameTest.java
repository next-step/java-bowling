package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static bowling.exception.BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void fallenPinsIsOverSpare_ThrowExp() {
        assertThatThrownBy(() -> frame.determineSpare(11))
                .hasMessageContaining(INVALID_COUNT_OF_FALLEN_PINS.getMessage());
    }
}
