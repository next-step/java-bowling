package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ScoreTest {
    @Test
    void 현재_점수와_더_계산할_횟수를_가지는_스코어_클래스를_생성한다() {
        //given
        int testScore = 1;
        int remainingAddition = 0;

        //when
        Score score = Score.of(testScore, remainingAddition);

        //then
        assertThat(score).isNotNull();
    }

    @Test
    void 더_계산할_수_있으면_점수_반환시_예외가_발생한다() {
        //given
        int testScore = 1;
        int remainingAddition = 1;
        Score score = Score.of(testScore, remainingAddition);

        //when
        //then
        assertThatExceptionOfType(UndoneCalculationException.class)
                .isThrownBy(() -> {
                    score.getScore();
                });
    }
}
