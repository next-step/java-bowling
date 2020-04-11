package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ScoreTest {
    @Test
    @DisplayName("스코어테스트")
    public void scoreTest() {
        assertThatThrownBy(() -> new Score(-1)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("0~10 입력");
    }
}
