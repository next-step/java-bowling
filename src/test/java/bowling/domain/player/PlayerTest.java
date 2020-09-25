package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "def", "ghi"})
    void createTest(String name) {
        Player player = Player.of(name);
        assertThat(player).isEqualTo(Player.of(name));
    }

    @DisplayName("Player 생성 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "아무개", "a!a"})
    void validateNameTest(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.of(name));
    }
}