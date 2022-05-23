package bowling.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {
    @Test
    void name() {
        assertThatThrownBy(() -> new PlayerName("a")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PlayerName("abcd")).isInstanceOf(IllegalArgumentException.class);
    }
}
