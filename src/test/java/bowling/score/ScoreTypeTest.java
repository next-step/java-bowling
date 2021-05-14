package bowling.score;

import bowling.entity.Pin;
import bowling.entity.frame.NormalFrame;
import bowling.entity.score.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreTypeTest {

    private LinkedList<Pin> pins;

    @BeforeEach
    public void setup(){
        pins = new LinkedList<>();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 스트라이크")
    public void scoreTypeStrike() {

        pins.add(new Pin(10));

        ScoreTypeEnum scoreType = ScoreTypeEnum.findScoreType(1, true);
        ScoreType strike = scoreType.createScoreType(pins);

        assertThat(strike instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 일반 점수")
    public void scoreTypeNormalScore() {
        pins.add(new Pin(5));

        ScoreTypeEnum scoreType = ScoreTypeEnum.findScoreType(1, false);
        ScoreType normalScore = scoreType.createScoreType(pins);

        assertThat(normalScore instanceof NormalScore).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 스페어")
    public void scoreTypeSpare() {
        pins.add(new Pin(5));
        pins.add(new Pin(5));

        ScoreTypeEnum scoreType = ScoreTypeEnum.findScoreType(2, true);
        ScoreType spare = scoreType.createScoreType(pins);

        assertThat(spare instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("쓰러트린 핀 결과가 미스")
    public void scoreTypeMiss() {
        pins.add(new Pin(5));
        pins.add(new Pin(4));

        ScoreTypeEnum scoreType = ScoreTypeEnum.findScoreType(2, false);
        ScoreType miss = scoreType.createScoreType(pins);

        assertThat(miss instanceof Miss).isTrue();
    }
}
