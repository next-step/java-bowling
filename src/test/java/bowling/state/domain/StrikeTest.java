package bowling.state.domain;

import bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {

    @Test
    @DisplayName("Strike 상태에서는 추가적으로 공을 굴릴 수 없다.")
    void throwException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Strike.of().roll(Pin.of(10)));
    }

    @Test
    @DisplayName("Strike 상태는 종료(True) 값을 가진다.")
    void isEnd() {
        assertThat(Strike.of().isEnd()).isTrue();
    }

}
