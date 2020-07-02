package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {
    @Test
    @DisplayName("생성 테스트")
    void init() {
        assertThatCode(() -> Pins.init()).doesNotThrowAnyException();
        assertThatCode(() -> Pins.down(10)).doesNotThrowAnyException();

    }

    @Test
    @DisplayName("핀 값 검증")
    void validate() {
        assertThatThrownBy(() -> {
            Pins.down(20);
            Pins.down(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
