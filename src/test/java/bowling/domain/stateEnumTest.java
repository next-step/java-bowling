package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class stateEnumTest {
    @DisplayName("Ready 상태인지 확인")
    @Test
    void value_ready() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(StateEnum.isReady(normalFrame.firstState()));
        assertThat(StateEnum.isReady(normalFrame.secondState()));
    }

    @DisplayName("FirstBowl 상태인지 확인")
    @Test
    void value_firstBowl() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(3);

        assertThat(StateEnum.isFirstBowl(normalFrame.firstState()));
    }

    @DisplayName("Strike 상태인지 확인")
    @Test
    void value_strike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(10);

        assertThat(StateEnum.isStrike(normalFrame.firstState()));
    }

    @DisplayName("Spare 상태인지 확인")
    @Test
    void value_spare() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(3);
        normalFrame.delivery(7);

        assertThat(StateEnum.isSpare(normalFrame.secondState()));
    }

    @DisplayName("MISS 상태인지 확인")
    @Test
    void value_() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(3);
        normalFrame.delivery(5);

        assertThat(StateEnum.isMiss(normalFrame.secondState()));
    }

    @DisplayName("GUTTER 상태인지 확인")
    @Test
    void value() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(0);
        normalFrame.delivery(0);

        assertThat(StateEnum.isGutter(normalFrame.firstState()));
        assertThat(StateEnum.isGutter(normalFrame.secondState()));
    }
}