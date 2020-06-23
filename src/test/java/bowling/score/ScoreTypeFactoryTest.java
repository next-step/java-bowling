package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeFactoryTest {

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 10이면 Strike를 반환")
    @Test
    public void makeScoreType_스트라이크() {
        Score score = Score.valueOf(10);

        ScoreType scoreType = ScoreTypeFactory.initiate(score);

        assertThat(scoreType).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 0이면 Gutter를 반환")
    @Test
    public void makeScoreType_거터() {
        Score score = Score.valueOf(0);

        ScoreType scoreType = ScoreTypeFactory.initiate(score);

        assertThat(scoreType).isEqualTo(ScoreType.GUTTER);
    }

    @DisplayName("처음 ScoreType을 만들 때 hitCount가 1~9이면 Normal을 반환")
    @Test
    public void makeScoreType_노멀() {
        Score score = Score.valueOf(3);

        ScoreType scoreType = ScoreTypeFactory.initiate(score);

        assertThat(scoreType).isEqualTo(ScoreType.NORMAL);
    }
}
