package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성")
    void createPlayer() {
        String name = "LKM";
        assertThat(Player.from(name)).isInstanceOf(Player.class);
    }

}