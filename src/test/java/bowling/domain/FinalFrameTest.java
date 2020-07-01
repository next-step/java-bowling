package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("마지막 프레임의 첫번째 경기떄 쓰러트린 볼링공의 갯수가 10개면 isFinished 를 true")
    @Test
    void finished() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.execute(BowlingPins.of(10));

        assertThat(finalFrame.isFinished()).isTrue();
    }

}