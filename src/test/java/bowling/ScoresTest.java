package bowling;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new Scores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new Scores(1, 2)).doesNotThrowAnyException();
        assertThatCode(() -> new Scores(1, 2, 3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 4개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Scores(1, 2, 3, 4));
    }

}
