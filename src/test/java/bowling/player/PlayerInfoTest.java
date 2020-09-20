package bowling.player;

import bowling.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerInfoTest {

    private PlayerInfo playerInfo;
    private Frames frames;

    @BeforeEach
    void setUp() {
        playerInfo = PlayerInfo.info();
    }

    @Test
    @DisplayName("PlayerInfo Map에 Player 이름을 Key, Payer를 Value로 값 저장")
    void saveInfo() {
        String name = "LHG";
        Player player = Player.of(name, frames);
        playerInfo.put(name, player);
        assertThat(playerInfo.size()).isEqualTo(1);
    }
}
