package bowling.score;

import bowling.entity.Pin;
import bowling.entity.score.NormalScore;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpareTest {
    private Pin firstPin;
    private Pin secondPin;
    private ScoreType spare;

    @BeforeEach
    void setup() {
        firstPin = new Pin(4);
        secondPin = new Pin(6);
        spare = new Spare(firstPin);
    }

    @Test
    @DisplayName("쓰러트린 핀 결과")
    public void missResult() {
        assertThat(spare.scoreResult().equals("4|/")).isTrue();
    }

    @Test
    @DisplayName("프레임 종료")
    public void isFrameEnd() {
        assertThat(spare.isFrameEnd()).isTrue();
    }
}
