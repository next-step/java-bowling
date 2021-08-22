package bowling.player;

import bowling.dto.PlayerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void initException() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            Player.init("PJSS");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void init() throws Exception {
        Assertions.assertThat(Player.init("PJS")).isInstanceOf(Player.class);
    }

    @Test
    void convert() throws Exception {
        Player pjs = Player.init("PJS");
        Assertions.assertThat(pjs.convert()).isInstanceOf(PlayerDto.class);
    }
}
