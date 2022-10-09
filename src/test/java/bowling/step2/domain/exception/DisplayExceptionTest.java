package bowling.step2.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DisplayExceptionTest {
    @Test
    @DisplayName("투구 결과 입력 예외 발생 테스트")
    void exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {throw new DisplayException();})
                .withMessage("해당 프레임이 끝나지 않은 상태입니다.");
    }
}