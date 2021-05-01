package bowling;

import bowling.entity.Pin;
import bowling.entity.score.Miss;
import bowling.entity.score.NormalScore;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MissTest {
    private int firstPin;
    private int secondPin;
    private ScoreType miss;
    private ScoreType normalScore;

    @BeforeEach
    void setup() {
        firstPin = 4;
        secondPin = 5;
        miss = new Miss(firstPin, secondPin);
        normalScore = new NormalScore(firstPin);
    }

    @Test
    @DisplayName("미스 생성")
    public void createMiss() {
        assertThat(miss.equals(normalScore.bowl(new Pin(secondPin)))).isTrue();
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

    @Test
    @DisplayName("미스 다음 10개 쓰러트릴 시 스트라이크")
    public void nextScoreIsStrike() {
        assertThat(miss.bowl(new Pin(10)) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("미스 다음 쓰러트린 핀이 10개 아닐 경우 일반 점수")
    public void nextScoreIsNormalScore() {
        assertThat(miss.bowl(new Pin(5)) instanceof NormalScore).isTrue();
    }
}
