package bowling.domain.score;

import bowling.domain.score.Pin;
import bowling.exception.BowlingPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {
    @Test
    void create() {
        Pin pin = new Pin(1);
        assertThat(pin).isEqualTo(new Pin(1));
    }

    @DisplayName("점수가 0점 미만이면 에러")
    @Test
    void error_min() {
        assertThatThrownBy(()->new Pin(-1)).isInstanceOf(BowlingPinException.class);
    }

    @DisplayName("점수가 10점을 초과하면 에러")
    @Test
    void error_max() {
        assertThatThrownBy(()->new Pin(11)).isInstanceOf(BowlingPinException.class);
    }
}