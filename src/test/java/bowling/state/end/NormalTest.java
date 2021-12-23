package bowling.state.end;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import org.junit.jupiter.api.Test;

class NormalTest {

    @Test
    void getPrintMarkTest() {
        Normal normal = new Normal(Pin.of(8));
        assertThat(normal.getPrintMark()).isEqualTo(String.valueOf(8));
    }
}