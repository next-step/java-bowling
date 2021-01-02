package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init();
    }

    @Test
    void bowlTest_Strike() {
        normalFrame = normalFrame.bowl(10);
        assertThat(normalFrame.isOver()).isTrue();
    }

    @Test
    void bowlTest_Miss() {
        normalFrame = normalFrame.bowl(6);
        assertThat(normalFrame.isOver()).isFalse();
    }

    @Test
    void getScoreTest() {
        Frame normalFrame1 = normalFrame.bowl(6).bowl(3);
        Frame normalFrame2 = normalFrame1.bowl(10);
        Frame normalFrame3 = normalFrame2.bowl(10);

        assertThat(normalFrame1.getScore()).isEqualTo(new Score(9,0));
    }

    @Test
    void isOverTest_Spare() {
        Frame normalFrame1 = normalFrame.bowl(8).bowl(2);
        assertThat(normalFrame1.isOver()).isTrue();
    }

    @Test
    void isOverTest_Strike() {
        Frame normalFrame1 = normalFrame.bowl(10);
        assertThat(normalFrame1.isOver()).isTrue();
    }

    @Test
    void isOverTest_BowledOnce() {
        Frame normalFrame1 = normalFrame.bowl(7);
        assertThat(normalFrame1.isOver()).isFalse();
    }

    @Test
    void isOverTest_Miss() {
        Frame normalFrame1 = normalFrame.bowl(5).bowl(2);
        assertThat(normalFrame1.isOver()).isTrue();
    }
}
