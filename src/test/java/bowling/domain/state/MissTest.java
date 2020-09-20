package bowling.domain.state;


import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @Test
    @DisplayName("Miss 상태에서 공을 굴릴 경우 예외발생 테스트")
    void miss_throw_exception() {
        assertThatThrownBy(() -> Miss.of(Pin.of(3), Pin.of(6)).bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("MISS 상태는 추가 투구 없이 종료(True) 값을 가지는 테스트")
    void miss_isEnd_test() {
        assertThat(Miss.of(Pin.of(3), Pin.of(6)).isEnd()).isTrue();
    }

}
