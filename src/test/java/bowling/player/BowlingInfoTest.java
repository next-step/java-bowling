package bowling.player;

import bowling.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingInfoTest {

    private BowlingInfo bowlingInfo;
    private Frames frames;

    @BeforeEach
    void setUp() {
        bowlingInfo = BowlingInfo.info();
    }

    @Test
    @DisplayName("PlayerInfo Map에 Player 이름을 Key, Payer를 Value로 값 저장")
    void saveInfo() {
        String name = "LHG";
        Player player = Player.of(name);
        frames = Frames.init();
        bowlingInfo.put(name, frames);
        assertThat(bowlingInfo.size()).isEqualTo(1);
    }
}
