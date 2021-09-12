package step3;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @DisplayName("bowl 메서드 기능 확인 테스트")
    @Test
    void test() {
        Score score = new Score(10, 2);
        score = score.bowl(4);
        assertThat(score).isEqualTo(new Score(14, 1));
    }
}
