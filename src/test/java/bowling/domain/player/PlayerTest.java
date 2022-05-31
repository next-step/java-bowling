package bowling.domain.player;

import org.junit.jupiter.api.Test;

import static bowling.domain.player.PlayerNameTest.NAME_ONE;
import static bowling.domain.player.PlayerNameTest.NAME_TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    public static final Player PLAYER_ONE = new Player(NAME_ONE);
    public static final Player PLAYER_TWO = new Player(NAME_TWO);

    @Test
    void Player는_PlayerName없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Player((PlayerName) null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
