package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerNameTest {

    @Test
    @DisplayName("")
    void create() {
        PlayerName name = new PlayerName("KANG");
        assertThat(name).isEqualTo(new PlayerName("KANG"));
    }


}