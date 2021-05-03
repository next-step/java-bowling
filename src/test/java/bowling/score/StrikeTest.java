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
    private ScoreType none;

    @BeforeEach
    void setup() {
        strikePin = new Pin(10);
        normalPin = new Pin(5);
        strike = new Strike();
        none = new None();
    }

    @Test
    @DisplayName("스트라이크 생성")
    public void createStrike() {
        assertThat(none.bowl(strikePin) instanceof Strike).isTrue();
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

    @Test
    @DisplayName("스트라이크 다음 10개 쓰러트릴 시 스트라이크")
    public void nextScoreIsStrike() {
        assertThat(strike.bowl(strikePin) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("스트라이크 다음 쓰러트린 핀이 10개 아닐 경우 일반 점수")
    public void nextScoreIsNormalScore() {
        assertThat(strike.bowl(normalPin) instanceof NormalScore).isTrue();
    }
}
