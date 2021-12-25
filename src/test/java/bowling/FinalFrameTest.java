package bowling;

import bowling.domain.FinalFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinalFrameTest {
    @Test
    void 마지막프레임이_맞는지_확인() {
        Assertions.assertAll(
                () -> assertTrue(new FinalFrame().isFinalFrame()),
                () -> assertFalse(new FinalFrame().isNinthFrame()));
    }
}
