package bowling.state;

import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {

    @Test
    @DisplayName("스트라이크 다음 볼을 던질경우")
    void strikeTest() {
        Strike strike = new Strike();
        assertThatThrownBy(() -> strike.bowl(3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
