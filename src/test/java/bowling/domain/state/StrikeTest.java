package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("점수 넘김 테스트")
    @Test
    void nextScore() {
        Strike strike = new Strike();
        assertThat(strike.isStrike()).isTrue();
        assertThat(strike.getScore()).isEqualTo("X");
    }
}
