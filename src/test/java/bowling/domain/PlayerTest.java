package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @DisplayName("3자 이상 오류")
    @Test
    void nameLengthCheck() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player player = new Player("1234");
        });
    }

}

