package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BowlingGameTest {

    @Test
    void 생성() {
        BowlingGame bowlingGame = new BowlingGame(new UserName("aaa"), Frames.start());
        assertAll(
            () -> assertThat(bowlingGame.getUserName().getName()).isEqualTo("aaa"),
            () -> assertThat(bowlingGame.isFinished()).isFalse(),
            () -> assertThat(bowlingGame.currentFrameNumber()).isEqualTo(1),
            () -> assertThat(bowlingGame.getFrames().size()).isEqualTo(1)
        );
    }
}
