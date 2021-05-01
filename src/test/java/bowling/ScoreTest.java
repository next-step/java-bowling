package bowling;

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

}
