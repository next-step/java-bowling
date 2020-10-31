package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrikeTest {
    private State strike;

    @BeforeEach
    void setUp() {
        strike = new Strike();
    }

    @Test
    @DisplayName("Strike 상태에서는 공을 굴릴 수 없다.")
    void isFinish() {
        assertThat(strike.isFinish()).isTrue();
    }

    @Test
    @DisplayName("Strike 상태에서는 공을 굴리려 하면 예외가 발생한다.")
    void bowlException() {
        assertThrows(IllegalStateException.class, () -> strike.bowl(5));
    }

    @Test
    @DisplayName("Strike 상태를 출력하면 X  가 출력된다.")
    void print() {
        assertThat(strike.print().trim()).isEqualTo("X");
    }
}
