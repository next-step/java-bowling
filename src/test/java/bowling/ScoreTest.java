package bowling;

import static org.assertj.core.api.Assertions.assertThat;
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

    @ValueSource(ints = {-1, 11})
    @ParameterizedTest(name = "[{arguments}] 점수는 0-10 사이가 아니면 예외가 발생한다.")
    void createExceptionTest(int input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Score.of(input));
    }

    @Test
    @DisplayName("점수 객체 캐싱이 정상적으로 동작한다.")
    void cacheTest() {
        assertThat(Score.of(10)).isSameAs(Score.of(10));
    }
}
