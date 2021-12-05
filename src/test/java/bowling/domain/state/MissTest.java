package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {
    @Test
    @DisplayName("Miss 예외 검증")
    void miss_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> Miss.of(Pins.from(3), Pins.from(4)).pitch(Pins.from(3)));
    }

    @Test
    @DisplayName("Miss 상태의 기록 검증")
    void miss_mark() {
        assertThat(Miss.of(Pins.from(3), Pins.from(4)).getMark()).isEqualTo("3|4");
    }

    @Test
    @DisplayName("Miss 상태의 기록 검증(거터 있는경우)")
    void miss_mark2() {
        assertThat(Miss.of(Pins.from(0), Pins.from(4)).getMark()).isEqualTo("-|4");
    }
}
