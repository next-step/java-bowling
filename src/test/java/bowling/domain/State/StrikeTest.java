package bowling.domain.State;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {
    @DisplayName("스트라이크가 아닐떄 예외를 발생시킨다")
    @Test
    void bowl() {
        assertThatThrownBy(() -> {
            new Strike().bowl(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
