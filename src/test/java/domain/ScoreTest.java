package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.Pins.STRIKE_PINS;
import static org.assertj.core.api.Assertions.assertThat;

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
    void 더_계산할_수_있는_동시에_상태가_Closed_된_경우에_계산된_점수를_반환한다() {
        //given
        Score strikeScore = Score.of(STRIKE_PINS, 2);
        int fallenPins = 5;

        //when
        Score updatedScore = strikeScore.update(fallenPins);

        //then
        assertThat(updatedScore).isEqualTo(Score.of(STRIKE_PINS + fallenPins, 1));
    }

    @ParameterizedTest
    @CsvSource({"0, 1", "1, 9", "10, 0", "20, 10"})
    void 더_계산할_수_없으면_점수를_업데이트해도_현재_스코어_객체를_반환한다(int score, int newScore) {
        //given
        Score incalculableScore = Score.of(score, 0);

        //when
        Score updatedScore = incalculableScore.update(newScore);

        //then
        assertThat(updatedScore).isEqualTo(incalculableScore);
    }
}
