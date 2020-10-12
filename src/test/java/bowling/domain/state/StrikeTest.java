package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    @DisplayName("Strike 상태 생성")
    @Test
    void create() {
        Strike strike = new Strike();
        assertThat(strike).hasToString("X");
    }

    @DisplayName("Strike 상태 투구 시 exception 발생")
    @Test
    void pitch() {
        Strike strike = new Strike();
        assertThatThrownBy(() -> strike.pitch(10))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
