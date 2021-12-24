package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("")
    void create() {
        Player player = new Player("KANG");
        Assertions.assertThat(player).isEqualTo(new Player("KANG"));
    }

}