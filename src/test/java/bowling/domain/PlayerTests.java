package bowling.domain;

import bowling.domain.exceptions.AlreadyStartedPlayerException;
import bowling.domain.exceptions.NotStartedPlayerException;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PlayerFrames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("첫 프레임이 아닌 상태에서 초구를 굴릴 수 없다.")
    @Test
    void bowlFirstValidationTest() {
        NormalFrame startFrame = NormalFrame.start(FIVE);
        Player bowledPlayer =
                new Player(PLAYER_NAME, new PlayerFrames(Collections.singletonList(startFrame)), startFrame);

        assertThatThrownBy(() -> bowledPlayer.bowlFirst(FIVE))
                .isInstanceOf(AlreadyStartedPlayerException.class);
    }

    @DisplayName("현재까지 진행한 결과를 확인 할 수 있다.")
    @Test
    void calculateTest() {
        NormalFrame startFrame = NormalFrame.start(FIVE);
        Player bowledPlayer =
                new Player(PLAYER_NAME, new PlayerFrames(Collections.singletonList(startFrame)), startFrame);

        assertThat(bowledPlayer.calculateResult())
                .contains(new FrameResults(Collections.singletonList(FrameResult.FIVE)));
    }

    @DisplayName("초구를 굴리지 않은 플레이어는 진행한 결과를 확인 할 수 없다.")
    @Test
    void calculateValidationTest() {
        Player player = Player.createByName(PLAYER_NAME);

        assertThatThrownBy(player::calculateResult).isInstanceOf(NotStartedPlayerException.class);
    }

    @DisplayName("현재 프레임이 마무리됐다면 다음 프레임을 진행할 수 있다.")
    @Test
    void bowlTest() {
        Player player = Player.createByName(PLAYER_NAME)
                .bowlFirst(TEN)
                .bowl(FIVE);

        assertThat(player).isEqualTo(new Player(PLAYER_NAME,
                new PlayerFrames(Arrays.asList(NormalFrame.start(TEN), NormalFrame.start(FIVE))),
                NormalFrame.start(FIVE)));
    }
}
