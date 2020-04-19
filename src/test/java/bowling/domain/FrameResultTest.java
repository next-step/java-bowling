package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FrameResultTest {

    @Test
    void addTotalScore() {
        FrameResult frameResult = new FrameResult("2|3", 5);
        int totalScore = frameResult.addTotalScore(10);
        assertThat(totalScore).isEqualTo(15);
    }

    @Test
    void addTotalScoreWhenUnScore() {
        FrameResult frameResult = new FrameResult("8|", -1);
        int totalScore = frameResult.addTotalScore(10);
        assertThat(totalScore).isEqualTo(-1);
    }
}
