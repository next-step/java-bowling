package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class SpareTest {

    private State spare;

    @BeforeEach
    void setup() {
        spare = Spare.of(Pins.from(5), Pins.from(5));
    }

    @Test
    @DisplayName("Spare 예외 검증")
    void spare_exception() {
        assertAll(() -> {
            assertThatIllegalArgumentException().isThrownBy(() -> spare.pitch(Pins.from(3)));
            assertThatIllegalArgumentException().isThrownBy(() -> Spare.of(Pins.from(5), Pins.from(4)));
        });
    }

    @Test
    @DisplayName("Spare 상태의 기록 검증")
    void spare_mark() {
        assertThat(spare.getMark()).isEqualTo("5|/");
    }
}
