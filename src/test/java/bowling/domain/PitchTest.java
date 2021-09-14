package bowling.domain;

import bowling.exception.OverScoreException;
import bowling.exception.PitchException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PitchTest {

    @Test
    @DisplayName("Pitch 의 값이 10 보다 크다면 PitchException")
    void pitch_over_10_exception() {
        assertThatThrownBy(() -> new Pitch(11))
                .isInstanceOf(PitchException.class);
    }

    @Test
    @DisplayName("Pitch 의 값이 0 보다 작다면 PitchException")
    void pitch_under_1_exception() {
        assertThatThrownBy(() -> new Pitch(-1))
                .isInstanceOf(PitchException.class);
    }

    @Test
    @DisplayName("기본 생성시 pitch 의 값은 -1")
    void pitch_default() {
        Pitch pitch = new Pitch();
        assertThat(pitch.nonRecode()).isTrue();
    }

    @Test
    @DisplayName("Pitch 의 값이 -1 이 아니라면 false")
    void pitch_value_not_minus_one() {
        Pitch pitch = new Pitch(1);
        assertThat(pitch.nonRecode()).isFalse();
    }

    @Test
    @DisplayName("Pitch 의 값이 10 이라면 true")
    void pitch_value_strike() {
        Pitch pitch = new Pitch(10);
        assertThat(pitch.isStrike()).isTrue();
    }

    @Test
    @Disabled
    @DisplayName("Pitch 2개의 합이 10 이상 이라면, OverScoreException")
    void pitch_sum_over_ten() {
        Pitch first = new Pitch(5);
        Pitch second = new Pitch(7);

        assertThatThrownBy(() -> first.checkTotal(second))
                .isInstanceOf(OverScoreException.class);
    }

    @Test
    @DisplayName("두 pitch 의 합이 10 이라면, true")
    void pitch_sum_spare() {
        assertAll(
                () -> {
                    Pitch first = new Pitch(5);
                    Pitch second = new Pitch(5);
                    assertThat(first.isSpare(second)).isTrue();
                },
                () -> {
                    Pitch first = new Pitch(1);
                    Pitch second = new Pitch(2);
                    assertThat(first.isSpare(second)).isFalse();
                }

        );
    }
}