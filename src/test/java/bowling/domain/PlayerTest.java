package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("이름을 인자로 받아 플레이어를 생성한다.")
    public void create() throws Exception {
        Player player = new Player("KSB");
        assertThat(player).isEqualTo(new Player("KSB"));
    }
}
