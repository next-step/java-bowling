package bowling.score;

import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.NormalScore;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StrikeTest {
    private Pin strikePin;
    private Pin normalPin;
    private ScoreType strike;

    @BeforeEach
    void setup() {
        strikePin = new Pin(10);
        normalPin = new Pin(5);
        strike = new Strike();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과")
    public void missResult() {
        assertThat(strike.scoreResult().equals("X")).isTrue();
    }

    @Test
    @DisplayName("프레임 종료")
    public void isFrameEnd() {
        assertThat(strike.isFrameEnd()).isTrue();
    }

}
