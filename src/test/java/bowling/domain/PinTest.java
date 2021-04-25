package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @DisplayName("핀을 생성한다")
    @Test
    void initTest() {
        Pin pin = new Pin(10);
        assertThat(pin).isEqualTo(new Pin(10));
    }

    @DisplayName("핀은 0 ~ 10의 값을 가진다")
    @Test
    void initExceptionTest() {
        assertThatThrownBy(() -> {
            new Pin(-1);
            new Pin(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
