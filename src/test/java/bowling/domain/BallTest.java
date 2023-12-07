package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BallTest {
    @Test
    void from_10() {
        Ball ball = Ball.from(10);
        assertThat(ball.getBallStatus()).isEqualTo(BallStatus.STIKE);
    }
    @Test
    void from_0() {
        Ball ball = Ball.from(0);
        assertThat(ball.getBallStatus()).isEqualTo(BallStatus.GUTTER);
    }
}
