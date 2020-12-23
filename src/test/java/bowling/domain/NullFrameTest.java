package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NullFrameTest {
    private NullFrame nullFrame = NullFrame.getInstance();

    @Test
    void isOverTest() {
        assertThat(nullFrame.isOver()).isFalse();
    }

    @Test
    void nextTest() {
        assertThat(nullFrame.next()).isEqualTo(nullFrame);
    }

    @Test
    void displayTest() {
        assertThat(nullFrame.display()).isEqualTo("  ");
    }
}
