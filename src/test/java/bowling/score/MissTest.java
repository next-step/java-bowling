package bowling.score;

import bowling.entity.score.Miss;
import bowling.entity.score.ScoreType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MissTest {
    private int firstPin;
    private int secondPin;
    private ScoreType miss;

    @BeforeEach
    void setup() {
        firstPin = 4;
        secondPin = 5;
        miss = new Miss(firstPin, secondPin);
    }

    @Test
    @DisplayName("쓰러트린 핀 결과")
    public void missResult() {
        assertThat(miss.scoreResult().equals("4|5")).isTrue();
    }

    @Test
    @DisplayName("프레임 종료")
    public void isFrameEnd() {
        assertThat(miss.isFrameEnd()).isTrue();
    }

}
