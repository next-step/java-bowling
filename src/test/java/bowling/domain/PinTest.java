package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    @Test
    @DisplayName("넘어뜨린 핀의 숫자가 10 이상이면 예외를 던진다.")
    void test1() {
        assertThatThrownBy(() -> {
            new Pin(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
