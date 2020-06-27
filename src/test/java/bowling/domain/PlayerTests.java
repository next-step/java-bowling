package bowling.domain;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import bowling.domain.exceptions.NotStartedPlayerException;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frameStatus.NormalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.FakeDataForTest.FIVE_IN_PROGRESS_NORMAL_FRAME;
import static bowling.domain.FakeDataForTest.STRIKE_FIRST_NORMAL_FRAME;
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

        assertThat(player).isEqualTo(new Player(PLAYER_NAME, null));
    }

    @DisplayName("초구를 던지지 않은 플레이어는 현재 프레임 완료 여부를 알 수 없다.")
    @Test
    void isCompletedValidationTest() {
        Player notBowlFirstPlayer = Player.createByName(PLAYER_NAME);

        assertThatThrownBy(notBowlFirstPlayer::isCurrentFrameCompleted)
                .isInstanceOf(NotStartedPlayerException.class);
    }

    @DisplayName("초구를 굴리고 굴린 결과 프레임을 반환할 수 있다.")
    @ParameterizedTest
    @MethodSource("bowlFirstRefactorResource")
    void bowlFirstRefactorTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME);
        Frame frame = initPlayer.bowlFirstRefactor(TEN);

        assertThat(frame).isEqualTo(STRIKE_FIRST_NORMAL_FRAME);
        assertThat(initPlayer.getCurrentFrame()).isEqualTo(frame);
    }
    public static Stream<Arguments> bowlFirstRefactorResource() {
        return Stream.of(
                Arguments.of(TEN, STRIKE_FIRST_NORMAL_FRAME),
                Arguments.of(FIVE, FIVE_IN_PROGRESS_NORMAL_FRAME)
        );
    }

    @DisplayName("현재 프레임이 완료되지 않았으면 현재 프레임을 진행할 수 있다.")
    @Test
    void bowlCurrentFrameRefactorTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME);
        initPlayer.bowlFirstRefactor(FIVE);
        NormalFrame expectedCurrentFrame = NormalFrame.start(FIVE).bowl(FIVE);

        Frame frame = initPlayer.bowlCurrentFrameRefactor(FIVE);

        assertThat(frame).isEqualTo(expectedCurrentFrame);
        assertThat(initPlayer.getCurrentFrame()).isEqualTo(expectedCurrentFrame);
    }

    @DisplayName("현재 프레임이 완료됐으면 현재 프레임을 진행할 수 없다.")
    @Test
    void bowlCurrentFrameRefactorValidationTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME);
        initPlayer.bowlFirstRefactor(TEN);

        assertThatThrownBy(() -> initPlayer.bowlCurrentFrameRefactor(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 프레임이 완료됐으면 다음 프레임을 진행할 수 있다.")
    @Test
    void bowlNextFrameRefactorTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME);
        initPlayer.bowlFirstRefactor(TEN);
        NormalFrame expectedCurrentFrame = new NormalFrame(
                2,
                NormalFrameStatus.bowlFirst(FIVE),
                NormalFrame.start(TEN)
        );

        Frame frame = initPlayer.toNextFrameRefactor(FIVE);

        assertThat(frame).isEqualTo(expectedCurrentFrame);
        assertThat(initPlayer.getCurrentFrame()).isEqualTo(expectedCurrentFrame);
    }

    @DisplayName("현재 프레임이 완료되지 않았으면 다음 프레임을 진행할 수 없다.")
    @Test
    void bowlNextFrameRefactorValidationTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME);
        initPlayer.bowlFirstRefactor(FIVE);

        assertThatThrownBy(() -> initPlayer.toNextFrameRefactor(TEN)).isInstanceOf(InvalidTryNextFrameException.class);
    }
}
