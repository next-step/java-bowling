package bowling.step2.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinCountExceededExceptionTest {
    @Test
    @DisplayName("핀 최대 개수 초과 입력 예외 발생 테스트")
    void exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {throw new PinCountExceededException();})
                .withMessage("핀 최대 개수를 초과하였습니다. 다시 입력해주세요.");
    }
}