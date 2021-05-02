package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @Test
    @DisplayName("score를 생성한다. 일반 score는 점수 계산이 가능하다.")
    void create() {
        // given
        // when
        final Score score = Score.of(9, 0);

        // then
        assertAll(
                () -> assertThat(score).isEqualTo(Score.of(9, 0)),
                () -> assertThat(score.canCalculate()).isTrue(),
                () -> assertThat(score.calculate()).isEqualTo(9)
        );
    }

    @Test
    @DisplayName("Strike Score를 생성한다. Strike는 점수 계산이 불가능하다.")
    void strike() {
        // given
        // when
        final Score strike = Score.strike();

        // then
        assertAll(
                () -> assertThat(strike).isEqualTo(Score.strike()),
                () -> assertThat(strike.canCalculate()).isFalse()
        );
    }

    @Test
    @DisplayName("Spare score를 생성한다. Spare는 점수 계산이 불가능하다.")
    void spare() {
        // given
        // when
        final Score spare = Score.spare();

        // then
        assertAll(
                () -> assertThat(spare).isEqualTo(Score.spare()),
                () -> assertThat(spare.canCalculate()).isFalse()
        );
    }
}
