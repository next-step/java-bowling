package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameServiceTest {
    @Test
    void illegalPlayerName() {
        Assertions.assertThatThrownBy(() -> new GameService("ABCD"))
                .isInstanceOf(IllegalPlayerNameException.class);
    }
}
