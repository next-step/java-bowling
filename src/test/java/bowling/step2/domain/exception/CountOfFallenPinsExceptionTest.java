package bowling.step2.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CountOfFallenPinsExceptionTest {
    @Test
    @DisplayName("입력 예외 발생 테스트")
    void exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {throw new CountOfFallenPinsException();})
                .withMessage("투구 결과를 잘못 입력하였습니다. 다시 입력해 주세요.");
    }
}