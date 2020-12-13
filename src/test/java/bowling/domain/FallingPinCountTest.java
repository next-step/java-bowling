package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FallingPinCountTest {

    @DisplayName("0에서 10 사이의 값이 아닌 자연를 입력하면 Exception")
    @Test
    void failFrom() {
        int given = 11;

        assertThatThrownBy(() -> FallingPinCount.fromFallingCount(given))
            .isInstanceOf(BowlingException.class)
            .hasMessage(String.format("넘어진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d", given));
    }
}