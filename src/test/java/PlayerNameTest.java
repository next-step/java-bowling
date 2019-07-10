import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNameTest {
    @Test
    void 플레이어의_이름을_입력받아서_생성한다() {
        String name = "tester";
        PlayerName playerName = PlayerName.from(name);

        assertThat(playerName.getName()).isEqualTo(name);
    }
}
