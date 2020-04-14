package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private FrameNumber frameNumber;

    @BeforeEach
    void setUp() {
        frameNumber = new FrameNumber(10);
    }

    @DisplayName("마지막 프레임은 다음 프레임이 존재하지 않는다.")
    @Test
    void getNextFrame() {
        Frame actual = new FinalFrame(frameNumber);

        assertThat(actual.getNext()).isEmpty();
    }
}