package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    @DisplayName("Strike 상태에서는 더 이상 공을 굴릴 수 없는 테스트")
    void strike_throw_exception_test() {
        assertThatThrownBy(() -> Strike.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Strike 상태는 종료(True)를 가지는 테스트")
    void strike_isEnd_test() {
        assertThat(Strike.of().isEnd()).isTrue();
    }

}
