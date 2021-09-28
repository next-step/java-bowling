package bowling.domain;

import bowling.exception.CannotCreateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinScoreTest {

    @Test
    @DisplayName("핀 점수 생성하기.")
    void createTest() throws CannotCreateException {
        PinScore ps = new PinScore(1);
        assertThat(ps.score()).isEqualTo(1);
    }

    @Test
    @DisplayName("점수는 0~10 숫자만 입력 가능하다.")
    void createValidationTest() {
        assertThatThrownBy(() -> new PinScore(11))
                .isInstanceOf(CannotCreateException.class);
    }

}