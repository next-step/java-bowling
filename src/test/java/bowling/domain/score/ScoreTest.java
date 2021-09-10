package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score Test")
class ScoreTest {

    @Test
    @DisplayName("1번이 남은 스코어 객체 생성")
    void generateRemainOneScore() {
        //given
        Score score = Score.ofRemainOne(10);

        //when
        boolean actual = score.isRemainCount(Score.BONUS_REMAIN_COUNT_ONE);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("2번이 남은 스코어 객체 생성")
    void generateRemainTwoScore() {
        //given
        Score score = Score.ofRemainTwo(10);

        //when
        boolean actual = score.isRemainCount(Score.BONUS_REMAIN_COUNT_TWO);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("스코어 점수 더하기 확인")
    void sum() {
        //given
        Score score = Score.ofRemainTwo(10);

        //when
        int actual = score.sum(10);

        //then
        assertThat(actual).isEqualTo(20);
    }
}