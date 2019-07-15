package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinsTest {

    @Test
    @DisplayName("기본 10점 보다 클때 예외처리")
    public void pinsException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pins.of(11);
        });
    }

    @Test
    @DisplayName("Equals")
    public void test() {
        assertThat(Pins.of(10).equals(Pins.of(10))).isTrue();
    }

    @Test
    @DisplayName("점수 합산")
    public void add() {
        assertThat(Pins.of(2).add(Pins.of(3))).isEqualTo(Pins.of(5));
    }

}