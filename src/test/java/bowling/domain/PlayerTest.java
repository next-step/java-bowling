package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체 생성")
    void create() {
        Player player = new Player("KYH");
        assertThat(player).isEqualTo(new Player("KYH"));
    }

}