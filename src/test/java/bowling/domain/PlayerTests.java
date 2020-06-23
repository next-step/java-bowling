package bowling.domain;

import bowling.domain.exceptions.AlreadyStartedPlayerException;
import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import bowling.domain.exceptions.NotStartedPlayerException;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PlayerFrames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

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

    @DisplayName("현재 프레임의 완료 여부를 알 수 있다.")
    @ParameterizedTest
    @MethodSource("isCompletedResource")
    void isCompletedTest(int numberOfHitPin, boolean expectedResult) {
        Player player = Player.createByName(PLAYER_NAME).bowlFirst(numberOfHitPin);
        assertThat(player.isCurrentFrameCompleted()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> isCompletedResource() {
        return Stream.of(
                Arguments.of(TEN, true),
                Arguments.of(FIVE, false)
        );
    }

    @DisplayName("초구를 던지지 않은 플레이어는 현재 프레임 완료 여부를 알 수 없다.")
    @Test
    void isCompletedValidationTest() {
        Player notBowlFirstPlayer = Player.createByName(PLAYER_NAME);

        assertThatThrownBy(notBowlFirstPlayer::isCurrentFrameCompleted)
                .isInstanceOf(NotStartedPlayerException.class);
    }

    @DisplayName("현재 프레임이 완료되지 않았으면 현재 프레임을 진행할 수 있다.")
    @Test
    void bowlCurrentFrameTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME).bowlFirst(FIVE);
        NormalFrame expectedNewFrame = NormalFrame.start(FIVE).bowl(FIVE);

        Player player = initPlayer.bowlCurrentFrame(FIVE);

        assertThat(player).isEqualTo(new Player(
                        PLAYER_NAME, new PlayerFrames(Collections.singletonList(expectedNewFrame)), expectedNewFrame));
    }

    @DisplayName("현재 프레임이 완료됐으면 현재 프레임을 진행할 수 없다.")
    @Test
    void bowlCurrentFrameValidationTest() {
        Player completedPlayer = Player.createByName(PLAYER_NAME).bowlFirst(TEN);

        assertThatThrownBy(() -> completedPlayer.bowlCurrentFrame(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 프레임이 완료됐으면 다음 프레임을 진행할 수 있다.")
    @Test
    void bowlNextFrameTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME).bowlFirst(TEN);
        Player player = initPlayer.nextFrame(TEN);

        assertThat(player).isEqualTo(new Player(PLAYER_NAME,
                new PlayerFrames(Arrays.asList(NormalFrame.start(TEN), NormalFrame.start(TEN).next(TEN))),
                NormalFrame.start(TEN).next(TEN)));
    }

    @DisplayName("현재 프레임이 완료되지 않았으면 다음 프레임을 진행할 수 없다.")
    @Test
    void bowlNextFrameValidationTest() {
        Player initPlayer = Player.createByName(PLAYER_NAME).bowlFirst(FIVE);

        assertThatThrownBy(() -> initPlayer.nextFrame(TEN)).isInstanceOf(InvalidTryNextFrameException.class);
    }
}
