package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "pjs"})
    @DisplayName("Player는 내부적으로 Name 객체를 가진다.")
    void create_player(String name) {
        assertThat(Player.of(name).getName()).isEqualTo(Name.of(name));
    }
}