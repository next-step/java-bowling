package bowling.domain;

import bowling.exception.DuplicatePlayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("플레이어들을 생성한다")
    @Test
    void initTest() {
        Players players = Players.from(Arrays.asList(Player.from("jdh"), Player.from("pan")));
        assertThat(players.howManyPlayers()).isEqualTo(2);
    }

    @DisplayName("동일한 이름의 플레이어는 생성할 수 없다")
    @Test
    void initExceptionTest() {
        assertThatThrownBy(()->Players.from(Arrays.asList(Player.from("jdh"), Player.from("jdh")))).isInstanceOf(DuplicatePlayerException.class);
    }
}
