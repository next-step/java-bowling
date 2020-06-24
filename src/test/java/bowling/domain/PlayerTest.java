package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Player 클래스 테스트")
public class PlayerTest {

    @DisplayName("Player 객체를 생성할 수 있다.")
    @Test
    void create() {
        String name = "SJ";
        Player player = new Player(name);

        assertThat(player).isEqualTo(new Player(name));
    }
}
