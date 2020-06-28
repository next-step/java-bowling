package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.ScoreType;
import java.util.Optional;
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

        assertThat(bonusPins.getScoreType()).isEqualTo(Optional.of(ScoreType.MISS));
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(1);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getScoreType()).isEqualTo(Optional.of(ScoreType.SPARE));
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike() {
        Pins bonusPins = FinalPins.newInstance();
        bonusPins.down(10);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getScoreType()).isEqualTo(Optional.of(ScoreType.STRIKE));
    }
}
