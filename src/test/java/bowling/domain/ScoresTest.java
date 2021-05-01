package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @DisplayName("점수들을 초기화한다")
    @Test
    void scoresInitTest() {
        Scores scores = Scores.init();
        assertThat(scores.size()).isEqualTo(0);
    }

    @DisplayName("점수는 이전 점수와 현재 라운드 점수의 합이다")
    @Test
    void scoresAddTest() {
        Scores scores = Scores.init();
        scores.addScore(Score.ofNone(9));
        scores.addScore(Score.ofNone(9));
        assertThat(scores.nthScore(2)).isEqualTo(18);
    }
}
