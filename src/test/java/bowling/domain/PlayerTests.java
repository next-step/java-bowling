package bowling.domain;

import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PlayerFrames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final String PLAYER_NAME = "JBJ";

    @DisplayName("이름을 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        Player player = Player.createByName(PLAYER_NAME);

        assertThat(player).isEqualTo(new Player(PLAYER_NAME, PlayerFrames.createEmpty(), null));
    }

    @DisplayName("초구를 굴려서 볼링 게임을 시작할 수 있다.")
    @Test
    void bowlFirstTest() {
        Player player = Player.createByName(PLAYER_NAME);
        NormalFrame startFrame = NormalFrame.start(FIVE);

        Player firstBowledPlayer = player.bowlFirst(FIVE);

        assertThat(firstBowledPlayer)
                .isEqualTo(new Player(PLAYER_NAME, new PlayerFrames(Collections.singletonList(startFrame)), startFrame));
    }
}
