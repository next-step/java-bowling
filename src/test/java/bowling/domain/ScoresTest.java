package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @DisplayName("타입별 스코어 생성")
    @Test
    void createScore() {
        Score score;
        score = Scores.createScore(new NormalFrame());
        assertThat(score instanceof ScoreNormal).isTrue();
        score = Scores.createScore(new FinalFrame());
        assertThat(score instanceof ScoreFinal).isTrue();
    }

}
