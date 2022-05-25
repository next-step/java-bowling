package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {
    @Test
    void 입력한_두_핀의_합이_10_이_넘을경우() {
        assertThatThrownBy(() -> {
            new Miss(5,9);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}