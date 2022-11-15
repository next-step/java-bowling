package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @Test
    void 생성_성공() {
        Frame2 frame = Frame2.createNormal();
        Bowling bowling = Bowling.from(frame, PinCount.of(5));
        assertThat(bowling.getResult()).isEqualTo(Result.MISS);
    }
}
