package bowling.step2.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlExceptionTest {
    @Test
    @DisplayName("bowl 예외 발생 테스트")
    void exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {throw new BowlException("예외입니다.");})
                .withMessage("예외입니다.");
    }
}