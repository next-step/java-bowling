package bowling.frame.domain;

import bowling.player.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @DisplayName("3자 이상 오류")
    @Test
    void nameLengthCheck() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player player = new Player("1234");
        });
    }

    @DisplayName("3자 필수")
    @Test
    void nameLengthCheck3() {
        assertThatCode(() -> new Player("123"))
                .doesNotThrowAnyException();
    }

}

