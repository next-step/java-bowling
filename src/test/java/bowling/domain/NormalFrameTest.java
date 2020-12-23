package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.first();
    }

    @Test
    void isOverTest() {
        normalFrame.add(10);
        assertThat(normalFrame.isOver()).isTrue();
    }

    @Test
    void displayTest() {
        normalFrame.add(7);
        normalFrame.add(3);
        assertThat(normalFrame.display()).isEqualTo("7/");
    }
}
