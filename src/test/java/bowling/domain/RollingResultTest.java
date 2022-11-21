package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RollingResultTest {

    @Test
    void 생성_성공() {
        Frame frame = Frame.createFirst();
        RollingResult rollingResult = RollingResult.createFirst(frame, PinCount.of(5));
        assertThat(rollingResult.getResult()).isEqualTo(Result.MISS);
    }

    @Test
    void 생성_다음() {
        Frame frame = Frame.createFirst();
        RollingResult before = RollingResult.createFirst(frame, PinCount.of(5));
        RollingResult after = before.createNext(frame, PinCount.of(5));

        assertThat(before.getAfter()).isEqualTo(after);
    }

    @Test
    void 점수_미스() {
        Frame frame = Frame.createFirst();
        RollingResult current = RollingResult.createFirst(frame, PinCount.of(5));
        assertThat(current.getScore()).isEqualTo(Score.of(5));
    }

    @Test
    void 점수_스트라이크() {
        Frame frame = Frame.createFirst();
        RollingResult next1 = RollingResult.createFirst(frame, 10);
        RollingResult next2 = next1.createNext(frame, 5);
        next2.createNext(frame, 5);

        assertThat(next1.getScore()).isEqualTo(Score.of(20));
    }

    @Test
    void 점수_스페어() {
        Frame frame = Frame.createFirst();
        frame.bowling(1);
        RollingResult next1 = RollingResult.createFirst(frame, 9);
        next1.createNext(frame, 5);

        assertThat(next1.getScore()).isEqualTo(Score.of(14));
    }
}

