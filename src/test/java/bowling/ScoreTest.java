package bowling;

import bowling.domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("점수가 300점을 초과하면 예외처리가 발생한다.")
    void throwExceptionWhenTotalOver10() {
        Assertions.assertThatThrownBy(() -> {
            new Score(301);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
