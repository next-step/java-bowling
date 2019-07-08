package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(0);
    }

    @Test
    void 다음프레임번호_안친상태() {
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(0);
    }
    
    @Test
    void 다음프레임번호_친상태() {
        normalFrame.doBowling(5);
        normalFrame.doBowling(5);
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(1);
    }
}
