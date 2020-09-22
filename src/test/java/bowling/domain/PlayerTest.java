package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @DisplayName("Player 생성")
    @Test
    void create() {
        Player player = new Player("PJS");
        assertThat(player).isEqualTo(new Player("PJS"));
    }

    @DisplayName("Player 생성 - 유효하지 않은 이름")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "포비님"})
    void create_invalidName(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
