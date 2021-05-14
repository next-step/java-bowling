package bowling.score;

import bowling.entity.Pin;
import bowling.entity.score.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalScoreTest {
    private Pin firstPin;
    private Pin secondPin;
    private ScoreType normalScore;

    @BeforeEach
    void setup() {
        firstPin = new Pin(4);
        secondPin = new Pin(6);
        normalScore = new NormalScore(firstPin);
    }

    @Test
    @DisplayName("쓰러트린 핀 결과")
    public void normalScoreResult() {
        assertThat(normalScore.scoreResult().equals("4")).isTrue();
    }

    @Test
    @DisplayName("프레임 진행 중")
    public void isFrameEnd() {
        assertThat(normalScore.isFrameEnd()).isFalse();
    }
}
