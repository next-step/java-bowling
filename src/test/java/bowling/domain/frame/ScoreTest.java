package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(Score.of(0,2)).isEqualTo(Score.of(0,2));
    }
}
