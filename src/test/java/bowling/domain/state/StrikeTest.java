package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {
    @Test
    @DisplayName("Strike 예외 검증")
    void strike_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> Strike.of().pitch(Pins.from(10)));
    }

    @Test
    @DisplayName("Strike 상태의 기록 검증")
    void strike_mark() {
        assertThat(Strike.of().mark()).isEqualTo("X");
    }
}
