package bowling.domain.frame;

import bowling.domain.frame.rolling.NormalRollings;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("일반 점수")
    void miss() {
        //given
        int score = 5;

        //when
        Score actual = Score.of(score);

        //then
        assertThat(actual).isEqualTo(Score.of(score));
        assertThat(actual.value()).isEqualTo(score);
        assertThat(actual.isFixed()).isTrue();

    }

    @Test
    @DisplayName("스트라이크 또는 스페어 이후 투구 없음")
    void spare_exception() {
        //given
        int first = 5;
        int second = 5;
        NormalRollings normalRollings = NormalRollings.first(first).second(second);
        Score score = Score.of(normalRollings);

        //when
        int actual = score.value();

        //then
        assertThat(actual).isEqualTo(-1);
        assertThat(score.isFixed()).isFalse();

    }

    @Test
    @DisplayName("스페어 점수계산 완료")
    void spare() {
        //given
        int first = 5;
        int second = 5;
        int bonus = 5;
        NormalRollings normalRollings = NormalRollings.first(first).second(second);
        Score score = Score.of(normalRollings);

        //when
        Score actual = score.plus(bonus);

        //then
        assertThat(actual.isFixed()).isTrue();
        assertThat(actual).isEqualTo(Score.of(15));

    }

    @Test
    @DisplayName("점수계산 완료 이후 점수 추가 예외")
    void plus_exception() {
        //given
        int first = 5;
        int bonus = 5;
        Score score = Score.of(first);

        //when
        ThrowableAssert.ThrowingCallable actual = () -> score.plus(bonus);

        //then
        assertThatThrownBy(actual).isInstanceOf(ScoreFixedException.class)
                .hasMessage("이미 점수 계산이 완료되었습니다. 보너스 점수를 추가할 수 없습니다.");

    }

    @Test
    @DisplayName("스트라이크 ")
    void strike() {
        //given
        int first = 10;
        int firstBonus = 5;
        int secondBonus = 5;
        NormalRollings normalRollings = NormalRollings.first(first);
        Score score = Score.of(normalRollings);
        Score strike = score.plus(firstBonus);

        //when
        Score actual = strike.plus(secondBonus);

        //then
        assertThat(actual.isFixed()).isTrue();
        assertThat(actual).isEqualTo(Score.of(20));

    }

}
