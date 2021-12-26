package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundTest {


    @Test
    @DisplayName("라운드의 Round는 값은 0-9 이다.")
    void createValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> Round.of(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> Round.of(10));
    }
}