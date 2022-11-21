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
}

