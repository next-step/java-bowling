package bowling.step2.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerNameExceptionTest {
    @Test
    @DisplayName("playerName 예외 발생 테스트")
    void exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {throw new PlayerNameException();})
                .withMessage("플레이어 이름 형식이 잘못되었습니다. 다시 입력해주세요.");
    }
}