package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {
    @Test
    @DisplayName("PlayerName은 무조건 3글자여야 함")
    void name() {
        assertThatThrownBy(() -> new PlayerName("a")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PlayerName("abcd")).isInstanceOf(IllegalArgumentException.class);
    }
}
