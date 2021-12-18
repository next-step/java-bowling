package bowling;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {

    @Test
    @DisplayName("점수가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> Score.of(10)).doesNotThrowAnyException();
    }

    @ValueSource(ints = {-1, 301})
    @ParameterizedTest(name = "[{arguments}]점수는 0-300 이어야 한다.")
    void createExceptionTest(int input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Score.of(input));
    }
}
