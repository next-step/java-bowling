package bowling.state.domain;

import bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GutterTest {

    @Test
    @DisplayName("Gutter 상태에서는 추가적으로 공을 굴릴 수 없다.")
    void throwException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Gutter.of().roll(Pin.of(10)));
    }

    @Test
    @DisplayName("Gutter 상태는 종료(True) 값을 가진다.")
    void isEnd() {
        assertThat(Gutter.of().isEnd()).isTrue();
    }

}
