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
            Ball ball = new Ball();
            ball.fallDown(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("pin의 갯수가 10개면 isStrike결과가 true인지 확인한다.")
    void isStrike() {
        Ball expectStrikeBall = new Ball();
        expectStrikeBall.fallDown(10);

        Ball expectNotStrikeBall = new Ball();
        expectNotStrikeBall.fallDown(5);

        assertThat(expectStrikeBall.isStrike()).isTrue();
        assertThat(expectNotStrikeBall.isStrike()).isFalse();
    }
}
