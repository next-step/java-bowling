package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"HDK", "WOO", "NHA"})
    void from(String name) {
        Player player = Player.from(name);

        assertThat(player).isEqualTo(Player.from(name));
    }
}
