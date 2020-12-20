package bowling.domain.score;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;
import bowling.exception.ValidOverPointException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = BasicScore.initFirst();
    }

    @Test
    @DisplayName("포인트 합산 처리를 한다.")
    void create() {
        score.pitch(Point.valueOf(2));
        score.pitch(Point.valueOf(8));

        assertThat(score.sumPoint()).isEqualTo(10);
    }

    @Test
    @DisplayName("점수를 넘는 양은 피칭을 할 수가 없다.")
    void throwOverPitch() {
        score.pitch(Point.valueOf(2));

        assertThatThrownBy(() ->
                score.pitch(Point.valueOf(9))).isInstanceOf(ValidOverPointException.class);
    }

    @Test
    @DisplayName("세번이상 피칭을 할수가 없다.")
    void throwHasScoreTurn() {
        score.pitch(Point.valueOf(2));
        score.pitch(Point.valueOf(1));
        assertThatThrownBy(() ->
                score.pitch(Point.valueOf(1))).isInstanceOf(NotHasTurnException.class);
    }


    @Test
    @DisplayName("미스이다.")
    void miss() {
        score.pitch(Point.valueOf(2));
        score.pitch(Point.valueOf(3));

        assertThat(score.getBowlType()).isEqualTo(BowlType.MISS);
    }

    @Test
    @DisplayName("스트라이크 처리가 된다.")
    void strike() {
        score.pitch(Point.valueOf(10));

        assertThat(score.getBowlType()).isEqualTo(BowlType.STRIKE);
    }

    @Test
    @DisplayName("스페어 처리가 된다.")
    void spare() {
        score.pitch(Point.valueOf(2));
        score.pitch(Point.valueOf(8));
        assertThat(score.getBowlType()).isEqualTo(BowlType.SPARED);
    }

    @Test
    @DisplayName("아직 피칭이 끝나진 않았다.")
    void none() {
        score.pitch(Point.valueOf(2));

        assertThat(score.getBowlType()).isEqualTo(BowlType.NONE);
    }




}