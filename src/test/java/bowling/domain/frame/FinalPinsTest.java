package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalPinsTest {

    @Test
    void invalid_negative() {
        Pins bonusPins = FinalPins.newInstance();

        assertThatThrownBy(() -> bonusPins.down(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_more_than_10() {
        Pins bonusPins = FinalPins.newInstance();

        assertThatThrownBy(() -> bonusPins.down(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_state() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(2);

        assertThatThrownBy(() -> bonusPins.down(3))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void invalid_sum_of_down_pin() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(1);

        assertThatThrownBy(() -> bonusPins.down(10))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(2);

        assertThat(bonusPins.getPinsState())
            .isEqualTo(new PinsState(Arrays.asList(1, 2), Arrays.asList(ScoreType.MISS)));
        //assertThat(finalFrame.()).isEqualTo(frameResult);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getPinsState())
            .isEqualTo(new PinsState(Arrays.asList(1, 9, 9), Arrays.asList(ScoreType.SPARE)));
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(10);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getPinsState())
            .isEqualTo(new PinsState(Arrays.asList(10, 9, 9), Arrays.asList(ScoreType.STRIKE)));

    }
}
