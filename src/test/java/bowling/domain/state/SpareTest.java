package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @Test
    void 핀수의_합이_10_이_아닐경우() {
        assertThatThrownBy(() -> {
            new Spare(9,4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}