package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("스트라이크 반환 테스트")
    void returnStrike() {

        Score score = Score.of(10, true);
        assertThat(score).isEqualTo(Score.STRIKE);
    }

    @Test
    @DisplayName("스페어 반환 테스트")
    void returnSpare() {

        Score score = Score.of(10, false);
        assertThat(score).isEqualTo(Score.SPARE);
    }

    @Test
    @DisplayName("거터 반환 테스트")
    void returnGutter() {

        Score score = Score.of(0, false);
        assertThat(score).isEqualTo(Score.GUTTER);
    }

    @Test
    @DisplayName("미스 반환 테스트")
    void returnMiss() {

        Score score = Score.of(-1, false);
        assertThat(score).isEqualTo(Score.MISS);
    }
}
