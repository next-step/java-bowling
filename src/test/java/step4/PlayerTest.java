package step4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step4.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @DisplayName("플레이어 생성 테스트")
    @Test
    void createPlayer() {
        Player cat = new Player("cat", null);
        assertThat(cat).isEqualTo(new Player("cat", null));
        assertThat(cat.getName()).isEqualTo("cat");
    }

    @DisplayName("플레이어 생성 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"123", "catsbi", ""})
    void createPlayerWithException(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(()-> new Player(input, null)).withMessage(Player.ERROR_INVALID_NAME);
    }
}
