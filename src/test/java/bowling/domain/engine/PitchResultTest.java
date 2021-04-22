package bowling.domain.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PitchResultTest {

    @Test
    @DisplayName("0 이상 10 이하의 값이 아니면 예외 처리한다.")
    void throwExceptionIfValueIsOutOfRange() {
        assertAll(
            () -> assertThatThrownBy(() -> PitchResult.wrap(-1)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> PitchResult.wrap(11)).isInstanceOf(IllegalArgumentException.class)
        );
    }
    
}
