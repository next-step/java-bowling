package bowling.model.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    @DisplayName("스트라이크일 때 X 표시 테스트")
    void getDescTest() {
        Strike strike = new Strike();
        assertThat(strike.getDesc()).isEqualTo("X");
    }
}
