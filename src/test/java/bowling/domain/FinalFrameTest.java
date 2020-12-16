package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = FinalFrame.init();
    }

    @Test
    void isOverTest() {
        finalFrame.add(10);
        finalFrame.add(3);
        assertThat(finalFrame.isOver()).isFalse();
    }

    @Test
    void next() {
        assertThat(finalFrame.next()).isEqualTo(NullFrame.getInstance());
    }

    @Test
    void displayTest() {
        finalFrame.add(10);
        finalFrame.add(10);
        finalFrame.add(10);
        assertThat(finalFrame.display()).isEqualTo("XXX");
    }
}
