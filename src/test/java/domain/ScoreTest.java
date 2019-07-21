package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 현재_점수와_더_계산할_횟수를_가지는_스코어_클래스를_생성한다() {
        //given
        int point = 1;
        int remainingAddition = 1;

        //when
        Score score = Score.of(point, remainingAddition);

        //then
        assertThat(score).isNotNull();
    }
}
