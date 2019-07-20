package domain;

import domain.frame.FrameIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private static final int MINIMUM_FRAME_INDEX = 1;

    private Player player;
    private BowlingGame testGame;

    @BeforeEach
    void setUp() {
        String inputName = "hjs";
        player = Player.from(inputName);
        testGame = BowlingGame.from(player);
    }

    @Test
    void 플레이어_이름을_입력받아_볼링게임을_생성한다() {

        assertThat(testGame.getPlayer()).isEqualTo(player);
    }

    @Test
    void 볼링게임이_생성될_때_1번_프레임이_생성된다() {

        assertThat(testGame.getFrames().get(0).getIndex())
                .isEqualTo(FrameIndex.from(MINIMUM_FRAME_INDEX));
    }
}
