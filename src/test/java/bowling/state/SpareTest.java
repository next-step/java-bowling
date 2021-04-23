package bowling.state;

import bowling.domain.state.Spare;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpareTest {

    @Test
    @DisplayName("스페어 10 넘어가는지")
    void tenCheck() {
        Assertions.assertThatThrownBy(() -> new Spare(3, 9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
