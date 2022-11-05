package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {
    @Test
    @DisplayName("스트라이크 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Strike().bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("스트라이크 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Strike().isFinish()).isTrue();
    }
}
