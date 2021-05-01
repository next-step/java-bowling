package bowling;

import bowling.entity.Pin;
import bowling.entity.score.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreTest {

    private Pin halfFallenPin;
    private ScoreType none;
    private ScoreType normalScore;
    private ScoreType strike;
    private ScoreType spare;

    @BeforeEach
    void setup() {
        halfFallenPin = new Pin(5);
        none = new None();
        normalScore = new NormalScore(halfFallenPin);
        strike = new Strike();
        spare = new Spare(halfFallenPin);
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 스트라이크")
    public void scoreTypeStrike() {
        Pin fallenPin = new Pin(10);

        ScoreType strike = none.bowl(fallenPin);

        assertThat(strike instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 일반 점수")
    public void scoreTypeNormalScore() {
        ScoreType normalScore = none.bowl(halfFallenPin);
        ScoreType normalScoreAfterStrike = strike.bowl(halfFallenPin);
        ScoreType normalScoreAfterSpare = spare.bowl(halfFallenPin);

        assertThat(normalScore instanceof NormalScore).isTrue();
        assertThat(normalScoreAfterStrike instanceof NormalScore).isTrue();
        assertThat(normalScoreAfterSpare instanceof NormalScore).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 스페어")
    public void scoreTypeSpare() {
        ScoreType spare = normalScore.bowl(halfFallenPin);

        assertThat(spare instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 미스")
    public void scoreTypeMiss() {
        Pin fallenPin = new Pin(4);

        ScoreType miss = normalScore.bowl(fallenPin);

        assertThat(miss instanceof Miss).isTrue();
    }
}
