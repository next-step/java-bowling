package bowling.domain.state;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class stateEnumTest {
    @DisplayName("Ready 상태인지 확인")
    @Test
    void value_ready() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(StateEnum.isReady(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isRunning(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isReady(normalFrame.getSecondHalfFrameState()));
        assertThat(StateEnum.isRunning(normalFrame.getSecondHalfFrameState()));
    }

    @DisplayName("FirstBowl 상태인지 확인")
    @Test
    void value_firstBowl() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(3);

        assertThat(StateEnum.isFirstBowl(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isRunning(normalFrame.getFirstHalfFrameState()));
    }

    @DisplayName("Strike 상태인지 확인")
    @Test
    void value_strike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(10);

        assertThat(StateEnum.isStrike(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isFinished(normalFrame.getFirstHalfFrameState()));
    }

    @DisplayName("Spare 상태인지 확인")
    @Test
    void value_spare() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(3);
        normalFrame.bowl(7);

        assertThat(StateEnum.isSpare(normalFrame.getSecondHalfFrameState()));
        assertThat(StateEnum.isFinished(normalFrame.getSecondHalfFrameState()));
    }

    @DisplayName("MISS 상태인지 확인")
    @Test
    void value_() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(3);
        normalFrame.bowl(5);

        assertThat(StateEnum.isMiss(normalFrame.getSecondHalfFrameState()));
        assertThat(StateEnum.isFinished(normalFrame.getSecondHalfFrameState()));
    }

    @DisplayName("GUTTER 상태인지 확인")
    @Test
    void value() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.bowl(0);
        normalFrame.bowl(0);

        assertThat(StateEnum.isGutter(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isRunning(normalFrame.getFirstHalfFrameState()));
        assertThat(StateEnum.isGutter(normalFrame.getSecondHalfFrameState()));
    }
}