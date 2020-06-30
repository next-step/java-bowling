package bowling.domain;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.FakeDataForTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGamePlayerTests {
    @DisplayName("볼링게임 참여자 이름을 입력받아서 객체를 생성 할 수 있다.")
    @Test
    void createTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);

        assertThat(bowlingGamePlayer.checkWhereFrame()).isEqualTo(0);
    }

    @DisplayName("초구를 굴려서 게임을 진행하고 결과를 확인 할 수 있다.")
    @ParameterizedTest
    @MethodSource("bowlFirstResource")
    void bowlFirstTest(int numberOfHitPin, FrameResults frameResults, FrameScore frameScore) {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);

        List<BowlingGameResult> bowlingGameResults = bowlingGamePlayer.bowlFirst(numberOfHitPin);

        assertThat(bowlingGameResults.size()).isEqualTo(1);
        assertThat(bowlingGameResults.get(0)).isEqualTo(new BowlingGameResult(frameResults, frameScore));
    }
    public static Stream<Arguments> bowlFirstResource() {
        return Stream.of(
                Arguments.of(
                        TEN,
                        NORMAL_STRIKE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 10)
                ),
                Arguments.of(
                        FIVE,
                        NORMAL_FIVE_IN_PROGRESS_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 5)
                )
        );
    }

    @DisplayName("현재 프레임을 진행하고 전체 결과를 확인할 수 있다.")
    @Test
    void bowlCurrentFrameTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(FIVE);

        List<BowlingGameResult> bowlingGameResults = bowlingGamePlayer.bowlCurrentFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(1);
        assertThat(bowlingGameResults.get(0))
                .isEqualTo(new BowlingGameResult(NORMAL_FIVE_SPARE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 10)));
    }

    @DisplayName("스페어 프레임 후 프레임을 진행하고 결과를 확인 할 수 있다.")
    @Test
    void bowlCurrentFrameAfterSpareTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(FIVE);
        bowlingGamePlayer.bowlCurrentFrame(FIVE);

        List<BowlingGameResult> bowlingGameResults = bowlingGamePlayer.toNextFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(2);
        assertThat(bowlingGameResults.get(0))
                .isEqualTo(new BowlingGameResult(NORMAL_FIVE_SPARE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.COMPLETE, 15)));
        assertThat(bowlingGameResults.get(1))
                .isEqualTo(new BowlingGameResult(NORMAL_FIVE_IN_PROGRESS_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 5)));
    }

    @DisplayName("스트라이크 프레임 후 프레임을 진행하고 결과를 확인 할 수 있다.")
    @Test
    void bowlCurrentFrameAfterStrikeTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(TEN);
        bowlingGamePlayer.toNextFrame(FIVE);

        List<BowlingGameResult> bowlingGameResults = bowlingGamePlayer.bowlCurrentFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(2);
        assertThat(bowlingGameResults.get(0))
                .isEqualTo(new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)));
        assertThat(bowlingGameResults.get(1))
                .isEqualTo(new BowlingGameResult(NORMAL_FIVE_SPARE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 10)));
    }

    @DisplayName("이미 완료된 현재 프레임을 진행할 수 없다.")
    @Test
    void bowlCurrentFrameValidationTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(TEN);

        assertThatThrownBy(() -> bowlingGamePlayer.bowlCurrentFrame(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 프레임이 완료됐다면 다음 프레임으로 진행할 수 있다.")
    @Test
    void bowlToNextFrameTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(TEN);

        List<BowlingGameResult> bowlingGameResults = bowlingGamePlayer.toNextFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(2);
        assertThat(bowlingGameResults.get(0))
                .isEqualTo(new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT,
                        new FrameScore(FrameScoreStatus.NOT_READY, 15)));
    }

    @DisplayName("현재 프레임이 완료되지 않은 상태에서 다음 프레임을 진행할 수 없다.")
    @Test
    void bowlToNextFrameValidationTest() {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(FIVE);

        assertThatThrownBy(() -> bowlingGamePlayer.toNextFrame(FIVE)).isInstanceOf(InvalidTryNextFrameException.class);
    }

    @DisplayName("현재 프레임의 완료 여부를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("isCompletedResource")
    void isCompleteTest(int numberOfHitPin, boolean expectedResult) {
        BowlingGamePlayer bowlingGamePlayer = BowlingGamePlayer.start(PLAYER_NAME);
        bowlingGamePlayer.bowlFirst(numberOfHitPin);

        assertThat(bowlingGamePlayer.isCurrentFrameCompleted()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> isCompletedResource() {
        return Stream.of(
                Arguments.of(FIVE, false),
                Arguments.of(TEN, true)
        );
    }
}
