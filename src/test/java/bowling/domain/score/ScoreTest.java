package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("정상 생성")
    void testCreateScore() {
        // given
        int score = 10;
        // when
        Score sc = new Score();
        sc.add(score);
        // then
        assertThat(sc.getScore()).isEqualTo(score);
    }
}
