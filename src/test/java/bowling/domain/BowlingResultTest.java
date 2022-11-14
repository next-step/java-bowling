package bowling.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingResultTest {

    @Test
    void 생성() {
        BowlingResult result = BowlingResult.from(Frame.createFinal(), 5);
        assertThat(result.getResult()).isEqualTo(Result.MISS);
    }

    @Test
    void 다음생성_스페어() {
        Frame frame = Frame.createFinal();
        frame.bowling(5);

        BowlingResult result = BowlingResult.from(frame, 5);

        assertThat(result.getResult()).isEqualTo(Result.SPARE);
    }
}
