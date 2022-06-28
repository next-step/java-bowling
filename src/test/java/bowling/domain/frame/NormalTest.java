package bowling.domain.frame;

import bowling.exception.BowlingExceptionCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalTest {
    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @Test
    void pinsGiven_addPin() {
        frame.addPins(3);
        assertThat(frame.pins().first()).isEqualTo(3);
    }

    @Test
    void tryNoIs1Given_returnSelf() {
        frame = new NormalFrame(new FrameProperties(1, new FallenPins(List.of(1)), 1));
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(frame);
    }

    @Test
    void tryNoIs0Given_returnNextFrame() {
        frame = new NormalFrame(new FrameProperties(1, new FallenPins(List.of(1,3)), 0));
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(new NormalFrame(2));
    }

    @Test
    void tryNoIs0AndIndexIs9Given_returnFinalFrame() {
        frame = new NormalFrame(new FrameProperties(9, new FallenPins(List.of(1,3)), 0));
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(new FinalFrame(10));
    }

    @Test
    void strikeIsGiven_returnNextFrame() {
        frame = new NormalFrame(new FrameProperties(1, new FallenPins(List.of(10)), 1));
        assertThat(frame.validateMoveToNextFrame()).isEqualTo(new NormalFrame(2));
    }

    @Test
    void fallenPinsIsOverSpare_ThrowExp() {
        assertThatThrownBy(() -> frame.determineSpare(11))
                .hasMessageContaining(BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS.getMessage());
    }
}
