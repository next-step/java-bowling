package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 참여자를_생성한다() {
        //given
        Player player = Player.from("ABC");
        //when
        //then
        assertThat(player).isEqualTo(Player.from("ABC"));
    }
}
