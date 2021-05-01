package bowling;

import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.NormalScore;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NoneTest {
    private Pin normalPin;
    Pin strikePin;
    private ScoreType none;

    @BeforeEach
    void setup() {
        normalPin = new Pin(4);
        strikePin = new Pin(10);
        none = new None();
    }

    @Test
    @DisplayName("프레임 시작")
    public void isFrameEnd() {
        assertThat(none.isFrameEnd()).isFalse();
    }

    @Test
    @DisplayName("다음 10개 쓰러트릴 시 스트라이크")
    public void nextScoreIsStrike() {
        assertThat(none.bowl(strikePin) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("다음 쓰러트린 핀이 10개 아닐 경우 일반 점수")
    public void nextScoreIsNormalScore() {
        assertThat(none.bowl(normalPin) instanceof NormalScore).isTrue();
    }
}
