import bowling.Player;
import bowling.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("1명미만 에러 확인")
    public void exception() throws Exception {
        assertThatThrownBy(() -> Players.of(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
