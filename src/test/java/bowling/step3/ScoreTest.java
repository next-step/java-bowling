package bowling.step3;

import bowling.step3.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @ParameterizedTest
    @DisplayName("생성 테스트")
    @ValueSource(ints = {1,3,6,10})
    void create(int inputScore){
        Score score = new Score(inputScore,0);
        assertThat(score).isEqualTo(new Score(inputScore,0));
    }

    @Test
    @DisplayName("스트라이크나 스페어시 보너스 점수 더하기 남은 여부확인")
    void bonus_remain_count_test(){
        Score score = new Score(10,2);
        assertThat(score.isFinishedScore()).isFalse();

        Score score2 = new Score(5,0);
        assertThat(score2.isFinishedScore()).isTrue();
    }

    @Test
    @DisplayName("getter 테스트")
    void getter(){
        Score score = new Score(7,0);
        assertThat(score.getScore()).isEqualTo(7);
    }
}
