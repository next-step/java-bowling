package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    @Test
    void create() {
        Strike strike = new Strike();
        assertThat(strike).hasToString("X");
    }

    @Test
    void pitch() {
        Strike strike = new Strike();
        assertThatThrownBy(() -> strike.pitch(10))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
