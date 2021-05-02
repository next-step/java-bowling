package bowling.domain.score;

import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @Test
    @DisplayName("score를 생성한다.")
    void create() {
        // given
        // when
        final Score score = Score.of(9, 0);

        // then
        assertThat(score).isEqualTo(Score.of(9, 0));
    }

    @Test
    @DisplayName("notcalculable score를 생성한다. noncalculable score는 점수 계산이 불가능하다.")
    void notCalculable() {
        // given
        // when
        final Score score = Score.notCalculable();

        // then
        assertThat(score.canCalculate()).isFalse();
    }

    @Test
    @DisplayName("normal score를 생성한다. 일반 score는 점수 계산이 가능하다.")
    void normal() {
        // given
        // when
        final Score score = Score.normal(9);

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

    @Test
    @DisplayName("점수계산이 불가능한 경우 calculate를 호출하면, 예외를 반환한다.")
    void cannotCalculate() {
        // given
        final Score score = Score.spare();

        // when
        // then
        assertThatThrownBy(() -> score.calculate())
                .isInstanceOf(CannotCalculateException.class)
                .hasMessage(CannotCalculateException.CANNOT_CALCULATE);
    }
}
