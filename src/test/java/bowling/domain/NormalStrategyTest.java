package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalStrategyTest {

    @Test
    void 프레임종료_판단() {
        NormalStrategy strategy = new NormalStrategy();

        assertThat(strategy.isFinal()).isFalse();
        assertThat(strategy.isFrameEnd(2, Result.MISS)).isTrue();
        assertThat(strategy.isFrameEnd(1, Result.STRIKE)).isTrue();
    }

    @Test
    void 점수_미스() {
        NormalStrategy strategy = new NormalStrategy();
        Frame frame = Frame.createFirst();
        RollingResult current = RollingResult.createFirst(frame, PinCount.of(5));
        assertThat(strategy.getScore(current)).isEqualTo(Score.of(5));
    }

    @Test
    void 점수_스트라이크() {
        NormalStrategy strategy = new NormalStrategy();
        Frame frame = Frame.createFirst();
        RollingResult next1 = RollingResult.createFirst(frame, 10);
        RollingResult next2 = next1.createNext(frame, 5);
        next2.createNext(frame, 5);

        assertThat(strategy.getScore(next1)).isEqualTo(Score.of(20));
    }

    @Test
    void 점수_스페어() {
        NormalStrategy strategy = new NormalStrategy();
        Frame frame = Frame.createFirst();
        frame.bowling(1);
        RollingResult next1 = RollingResult.createFirst(frame, 9);
        next1.createNext(frame, 5);

        assertThat(strategy.getScore(next1)).isEqualTo(Score.of(14));
    }
}

