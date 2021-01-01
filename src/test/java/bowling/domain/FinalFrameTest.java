package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private NormalFrame _9thFrame;
    private Frame finalFrame;

    @BeforeEach
    void setUp() {
        _9thFrame = NormalFrame.of(9);
    }

    @Test
    void bowlTest() {
        finalFrame = _9thFrame.bowl(7).bowl(3).bowl(10);
        finalFrame = finalFrame.bowl(3).bowl(7);
        assertThat(finalFrame.isOver()).isTrue();
    }

    @Test
    void getScoreTest() {
        finalFrame = _9thFrame.bowl(7).bowl(3).bowl(10);
        finalFrame = finalFrame.bowl(6);
        finalFrame = finalFrame.bowl(3);
        assertThat(finalFrame.getScore().getFallenPins()).isEqualTo(19);
    }

    @Test
    void addBonusScoreTest_NotBowled() {
        finalFrame = _9thFrame.bowl(10);
        assertThat(_9thFrame.getScore().getFallenPins()).isEqualTo(-1);
    }

    @Test
    void addBonusScoreTest_Turkey() {
        finalFrame = _9thFrame.bowl(10).bowl(10).bowl(10);
        assertThat(_9thFrame.getScore().getFallenPins()).isEqualTo(30);
    }

    @Test
    void isOverTest_Miss() {
        finalFrame = new FinalFrame();
        finalFrame = finalFrame.bowl(3);
        finalFrame = finalFrame.bowl(5);
        assertThat(finalFrame.isOver()).isTrue();
    }

    @Test
    void isOverTest_SpareAndStrike() {
        finalFrame = new FinalFrame();
        finalFrame = finalFrame.bowl(3);
        finalFrame = finalFrame.bowl(7);
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isOver()).isTrue();
    }

    @Test
    void isOverTest_SpareAndNotStrike() {
        finalFrame = new FinalFrame();
        finalFrame = finalFrame.bowl(5);
        finalFrame = finalFrame.bowl(5);
        finalFrame = finalFrame.bowl(3);
        assertThat(finalFrame.isOver()).isTrue();
    }
    @Test
    void isOverTest_Double() {
        finalFrame = new FinalFrame();
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isOver()).isFalse();
    }

    @Test
    void isOverTest_Turkey() {
        finalFrame = new FinalFrame();
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);
        assertThat(finalFrame.isOver()).isTrue();
    }
}
