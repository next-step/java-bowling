package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("플레이어가 잘 생성되는지 확인한다.")
    @ValueSource(strings = "pobi,wani")
    void create(String name) {
        Player player = Player.of(name);
        assertThat(player).isEqualTo(Player.of(name));
    }



}
