package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {

    @Test
    void downPinFirstZeroAndSecondThree_thanGameOver() {
        // given
        Pins first = Pins.valueOf(5);
        Pins second = Pins.valueOf(1);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);

        // exception
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(()-> frame.nextFrame());
    }

    @Test
    void downPinOne_nextFrame() {
        // given
        Pins first = Pins.valueOf(5);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);

        assertThat(frame.nextFrame()).isEqualTo(frame);
    }

    @Test
    void downPinTwo_nextFrame() {
        // given
        Pins first = Pins.valueOf(5);
        Pins second = Pins.valueOf(5);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.nextFrame()).isEqualTo(frame);
    }

    @Test
    void downPin10And0_nextFrame() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(0);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.nextFrame()).isEqualTo(frame);
    }

    @Test
    void downPin0And10_nextFrame() {
        // given
        Pins first = Pins.valueOf(0);
        Pins second = Pins.valueOf(10);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.nextFrame()).isEqualTo(frame);
    }

    @Test
    void downPin0_10_10_nextFrame() {
        // given
        Pins first = Pins.valueOf(0);
        Pins second = Pins.valueOf(10);
        Pins third = Pins.valueOf(10);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);
        frame.bowl(third);

        // exception
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(()-> frame.nextFrame());
    }

    @Test
    void downPinFirstTen_strike() {
        // given
        Pins first = Pins.DOWN_ALL;

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);

        // then
        assertThat(frame.nextFrame()).isEqualTo(frame);
        assertThat(frame.getStates().get(0)).isInstanceOf(Strike.class);
    }

    @Test
    void downPinFirstTenSecondTen_strike() {
        // given
        Pins first = Pins.DOWN_ALL;
        Pins second = Pins.DOWN_ALL;

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);

        // then
        assertThat(frame.nextFrame()).isEqualTo(frame);
        assertThat(frame.getStates().get(0)).isInstanceOf(Strike.class);
        assertThat(frame.getStates().get(1)).isInstanceOf(Strike.class);
    }

    @Test
    void downPinUntilSecondSumTen_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(9);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);
        frame.bowl(third);

        // then
        assertThat(frame.getStates().get(0)).isInstanceOf(Strike.class);
        assertThat(frame.getStates().get(1)).isInstanceOf(Spare.class);
    }

    @Test
    void downPinSecondTenThirdTen_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(10);
        Pins third = Pins.valueOf(10);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);
        frame.bowl(third);

        // then
        assertThat(frame.getStates().get(0)).isInstanceOf(Strike.class);
        assertThat(frame.getStates().get(1)).isInstanceOf(Strike.class);
        assertThat(frame.getStates().get(2)).isInstanceOf(Strike.class);
    }

    @Test
    void downPinFirstNotTenUntilSecondSumTenThirdTen() {
        // given
        Pins first = Pins.valueOf(1);
        Pins second = Pins.valueOf(9);
        Pins third = Pins.valueOf(10);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);
        frame.bowl(third);

        // then
        assertThat(frame.getStates().get(0)).isInstanceOf(Spare.class);
        assertThat(frame.getStates().get(1)).isInstanceOf(Strike.class);
    }

    @Test
    void downPinFirstTenSecondNotTenSecondThirdSumTen_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(0);
        Pins third = Pins.valueOf(10);

        // when
        FinalFrame frame = FinalFrame.of();
        frame.bowl(first);
        frame.bowl(second);
        frame.bowl(third);

        // then
        assertThat(frame.getStates().get(0)).isInstanceOf(Strike.class);
        assertThat(frame.getStates().get(1)).isInstanceOf(Spare.class);
    }
}