package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @Test
    void _10프레임_끝으로_게임종료() {
        finalFrame.doBowling(4);
        finalFrame.doBowling(6);
        assertThat(finalFrame.isGameOver()).isFalse();

        finalFrame.doBowling(10);
        assertThat(finalFrame.isGameOver()).isTrue();
    }
}
