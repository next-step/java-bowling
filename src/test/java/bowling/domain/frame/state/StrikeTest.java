package bowling.domain.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {
    @DisplayName("STRIKE 점수를 갖고올 수 있다.")
    @Test
    void strike() {
        State strike = new Strike();

        assertThat(strike.toString()).isEqualTo("X");
    }
}