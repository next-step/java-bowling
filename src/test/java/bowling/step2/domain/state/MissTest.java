package bowling.step2.domain.state;

import bowling.step2.domain.exception.BowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {
    public static final Miss MISS = new Miss(2, 3);
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatThrownBy(() -> MISS.bowl(1))
                .isInstanceOf(BowlException.class)
                .hasMessage("더이상 투구할 수 없습니다.");
    }
}