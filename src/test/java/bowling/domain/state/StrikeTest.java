package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {

    @Test
    @DisplayName("스트라이크 표시 테스트")
    void markTest() {
        Strike strike = new Strike();
        assertThat(strike.getMark()).isEqualTo("X");
    }
}
