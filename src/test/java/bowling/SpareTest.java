package bowling;

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
    private ScoreType normalScore;

    @BeforeEach
    void setup() {
        firstPin = new Pin(4);
        secondPin = new Pin(6);
        spare = new Spare(firstPin);
        normalScore = new NormalScore(firstPin);
    }

    @Test
    @DisplayName("스페어 생성")
    public void createSpare() {
        assertThat(spare.equals(normalScore.bowl(secondPin))).isTrue();
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

    @Test
    @DisplayName("스페어 다음 10개 쓰러트릴 시 스트라이크")
    public void nextScoreIsStrike() {
        assertThat(spare.bowl(new Pin(10)) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("스페어 다음 쓰러트린 핀이 10개 아닐 경우 일반 점수")
    public void nextScoreIsNormalScore() {
        assertThat(spare.bowl(firstPin) instanceof NormalScore).isTrue();
    }
}
