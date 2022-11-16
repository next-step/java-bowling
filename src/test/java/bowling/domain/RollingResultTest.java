package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RollingResultTest {

    @Test
    void 생성_성공() {
        Frame frame = Frame.createNormal();
        RollingResult rollingResult = RollingResult.from(frame, PinCount.of(5));
        assertThat(rollingResult.getResult()).isEqualTo(Result.MISS);
    }
}
