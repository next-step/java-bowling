package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {

    @Test
    @DisplayName("Strike 상태에선 더이상 투구할 수 없다.")
    void play() {
        Strike strike = new Strike();
        assertThatThrownBy(() -> strike.play(4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
