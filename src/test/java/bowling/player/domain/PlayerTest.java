package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름은 3글자를 초과하여 지정 할 수 없다.")
    void exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.of("Pray"));
    }

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
