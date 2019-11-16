package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallTest {

    @Test
    @DisplayName("Ball은 10까지만 pin을 가질 수 있다.")
    void pinIsUnderTen() {
        assertThatThrownBy(() -> {
            Ball ball = new Ball(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("pin의 갯수가 10개면 isStrike결과가 true인지 확인한다.")
    void isStrike() {
        assertThat(new Ball(10).isStrike()).isTrue();
        assertThat(new Ball(5 ).isStrike()).isFalse();
    }
}
