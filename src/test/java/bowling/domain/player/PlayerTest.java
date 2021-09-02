package bowling.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void test() {
        //given
        String name = "ABC";

        //when
        Player player = new Player(name);

        //then
        assertThat(player).isEqualTo(new Player(name));

    }


}
