package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFramePinsTest {

    @Test
    void invalid_negative() {
        Pins bonusPins = FinalFramePins.create();

        assertThatThrownBy(() -> bonusPins.down(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_more_than_10() {
        Pins bonusPins = FinalFramePins.create();

        assertThatThrownBy(() -> bonusPins.down(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalid_state() {
        Pins bonusPins = FinalFramePins.create();
        bonusPins.down(1);
        bonusPins.down(2);

        assertThatThrownBy(() -> bonusPins.down(3))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void invalid_sum_of_down_pin() {
        Pins bonusPins = FinalFramePins.create();
        bonusPins.down(1);

        assertThatThrownBy(() -> bonusPins.down(10))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    void miss() {
        Pins bonusPins = FinalFramePins.create();
        bonusPins.down(1);
        bonusPins.down(2);

        assertThat(bonusPins.getScoreType()).isEqualTo(ScoreType.MISS);
    }

    @Test
    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    void spare() {
        Pins bonusPins = FinalFramePins.create();
        bonusPins.down(1);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @Test
    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    void strike() {
        Pins bonusPins = FinalFramePins.create();
        bonusPins.down(10);
        bonusPins.down(9);
        bonusPins.down(9);

        assertThat(bonusPins.getScoreType()).isEqualTo(ScoreType.STRIKE);
    }
}
