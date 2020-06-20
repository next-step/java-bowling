package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTests {
    @DisplayName("이름을 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        String playerName = "JBJ";

        Player player = new Player(playerName);

        assertThat(player).isNotNull();
    }
}
