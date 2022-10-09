package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {
    public static final Spare SPARE = new Spare(new Score(4));
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SPARE.bowl(1))
                .withMessage("더이상 투구할 수 없습니다.");
    }
}