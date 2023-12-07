package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FrameTest {
    @Test
    void _1투구_반영_스트라이크() {
        Ball firstBall = Ball.from(10);
        Frame frame = Frame.fromFirstBall(firstBall);
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    void _2투구_반영_스페어O() {
        Ball firstBall = Ball.from(1);
        Frame frame = Frame.fromFirstBall(firstBall);

        Ball secondBall = Ball.from(9);
        frame.applySecondBall(secondBall);

        assertThat(frame.isSpare()).isTrue();
    }

    @Test
    void _2투구_반영_스페어X() {
        Ball firstBall = Ball.from(1);
        Frame frame = Frame.fromFirstBall(firstBall);

        Ball secondBall = Ball.from(3);
        frame.applySecondBall(secondBall);

        assertThat(frame.isSpare()).isFalse();
    }
}
