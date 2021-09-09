package bowling.model.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("볼링 게임 플레이어 테스트")
public class PlayerTest {

    @DisplayName("플레이어 이름이 알파벳 3글자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcd", "포비", "포비포비"})
    void playerNamePatternExceptionTest(String name) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(name))
                .withMessage("플레이어 이름은 알파벳 3글자로 이루어져야 합니다.");
    }
}