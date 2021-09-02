package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("객체 생성")
    void construct() {
        //given
        String name = "ABC";

        //when
        Player player = new Player(name);

        //then
        assertThat(player).isEqualTo(new Player(name));

    }


}
