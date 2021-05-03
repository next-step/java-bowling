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
    private ScoreType none;

    @BeforeEach
    void setup() {
        firstPin = new Pin(4);
        secondPin = new Pin(6);
        normalScore = new NormalScore(firstPin);
        none = new None();
    }

    @Test
    @DisplayName("일반 점수 생성")
    public void createNormalScore() {
        assertThat(normalScore.equals(none.bowl(firstPin))).isTrue();
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

    @Test
    @DisplayName("다음 쓰러트린 핀과의 합이 10개면 스페어")
    public void nextScoreIsStrike() {
        assertThat(normalScore.bowl(secondPin) instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("다음 쓰러트린 핀과의 합이 10개 아닐 경우 미스")
    public void nextScoreIsNormalScore() {
        assertThat(normalScore.bowl(new Pin(5)) instanceof Miss).isTrue();
    }
}
