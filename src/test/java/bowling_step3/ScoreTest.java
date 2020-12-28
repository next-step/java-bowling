package bowling_step3;

import bowling_step3.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("점수 생성")
    void 점수_생성() {
        Score score = new Score(10, 2);
        assertThat(score).isEqualTo(new Score(10, 2));

        score = new Score(4, 0);
        assertThat(score).isEqualTo(new Score(4, 0));
    }
    @Test
    @DisplayName("스페어 보너스 점수")
    void 스페어_보너스_점수() {
        Score score = new Score(10, 1);
        score.add(4);

        assertThat(score).isEqualTo(new Score(14, 0));
    }

    @Test
    @DisplayName("스트라이크 보너스 점수")
    void 스트라이크_보너스_점수() {
        Score score = new Score(10, 2);
        score.add(10);
        score.add(5);

        assertThat(score).isEqualTo(new Score(25, 0));
    }
}
