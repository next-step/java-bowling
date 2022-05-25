package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {
    @Test
    void 입력한_pin이_0_보다_작을_때() {
        assertThatThrownBy(() -> {
            new Pin(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력한_pin이_10_보다_클_때() {
        assertThatThrownBy(() -> {
            new Pin(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}