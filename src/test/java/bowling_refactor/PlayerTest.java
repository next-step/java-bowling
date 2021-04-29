package bowling_refactor;

import bowling_refactor.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 이름 3글자 아닌 경우 에러")
    void inputFourWord() {
        assertThatThrownBy(() -> new Player("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름이 숫자가 들어온 경우 에러")
    void inputNumber() {
        assertThatThrownBy(() -> new Player("111"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
