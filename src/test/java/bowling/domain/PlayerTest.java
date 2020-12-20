package bowling.domain;

import bowling.exception.PlayerNameLengthException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("플레이어가 잘 생성되는지 확인한다.")
    @ValueSource(strings = "POB,WAN")
    void create(String name) {
        Player player = Player.of(name);
        assertThat(player).isEqualTo(Player.of(name));
    }


    @Test
    @DisplayName("플레이어의 이름이 3글자를 초과하면 익셉션이 발생한다.")
    void throwNameLength() {
        Assertions.assertThatThrownBy(() -> Player.of("POBI"))
                .isInstanceOf(PlayerNameLengthException.class);
    }
}
