package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void fallenPinsIsOverSpare_ThrowExp() {
        assertThatThrownBy(() -> frame.determineSpare(11))
                .hasMessageContaining(INVALID_COUNT_OF_FALLEN_PINS.getMessage());
    }

    @Test
    void tryNoIs0Given_returnNextFrame() {
        frame = new FinalFrame(new FrameProperties(10, new FallenPins(List.of(1,2,3)), 0));
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(new FinalFrame(11));
    }

    @Test
    void tryNoIsNot0Given_returnSelf() {
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(new FinalFrame(10));
    }
}
