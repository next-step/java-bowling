package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerNameTest {

    @Test
    @DisplayName("사용자의 이름을 입력받아 생성할 수 있다.")
    void create() {
        // given
        final String name = "KSD";

        // when
        final PlayerName playerName = new PlayerName(name);

        // then

        assertThat(playerName).isEqualTo(new PlayerName(name));
    }
}
