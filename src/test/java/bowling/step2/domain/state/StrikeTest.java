package bowling.step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {
    public static final Strike STRIKE = new Strike();
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> STRIKE.bowl(1))
                .withMessage("더이상 투구할 수 없습니다.");
    }
}