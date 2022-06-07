package bowling.domain;

import org.junit.jupiter.api.Test;

public class FinalFrameTest {
    @Test
    void create() {
        NormalFrame normalFrame = NormalFrame.first(10);
        FinalFrame finalFrame = FinalFrame.first(10);
    }
}
