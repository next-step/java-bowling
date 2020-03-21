package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @Test
    @DisplayName("볼링 프레임 생성 테스트")
    void createFrameByBowling() {
        Bowling bowling = new Bowling();
        bowling.bowl(10);
        bowling.bowl(4);
        bowling.bowl(6);
        bowling.bowl(10);
        bowling.bowl(10);

        assertThat(bowling.getDefaultFrames().size()).isEqualTo(4);
    }
}
