package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void first() {
        NormalFrame normalFrame = Frame.first();
        assertThat(normalFrame.number()).isEqualTo(1);
    }

    @Test
    void last() {
        int finalFrameNumber = 10;
        FinalFrame finalFrame = Frame.last(finalFrameNumber);
        assertThat(finalFrame.number()).isEqualTo(finalFrameNumber);
    }





}
