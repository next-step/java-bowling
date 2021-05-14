package bowling.score;

import bowling.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("스코어 객체 생성")
    public void normalScoreSum(){
        int totalScore = 5;

        Score score = new Score(totalScore);

        assertThat(score.equals(new Score(totalScore))).isTrue();
    }

    @Test
    @DisplayName("스트라이크 스코어 계산")
    public void calculateStrikeScore(){
        Score score = new Score(10, 2);
        assertThat(score.calculate(10).calculate(10).score() == 30).isTrue();
    }

    @Test
    @DisplayName("스페어 스코어 계산")
    public void calculateSpareScore(){
        Score score = new Score(10, 1);
        assertThat(score.calculate(10).score() == 20).isTrue();
    }

    @Test
    @DisplayName("일반 스코어 계산")
    public void calculateScore(){
        Score score = new Score(9, 0);
        assertThat(score.score() == 9).isTrue();
    }

}
