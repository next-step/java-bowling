package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("두개의 스코어를 합한 결과를 반환")
    void sumTest() {
        Score score1 = new Score(1);
        Score score2 = new Score(1);

        assertThat(score1.sum(score2)).isEqualTo(new Score(2));
    }
}
