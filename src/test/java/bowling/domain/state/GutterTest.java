package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GutterTest {

    @Test
    @DisplayName("Gutter 상태에서 공을 굴릴 경우 예외발생 테스트")
    void gutter_throw_exception_test() {
        assertThatThrownBy(() -> Gutter.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Gutter 상태는 추가 투구 없이 종료(True) 값을 가지는 테스트")
    void gutter_isEnd_test() {
        assertThat(Gutter.of().isEnd()).isTrue();
    }
}
