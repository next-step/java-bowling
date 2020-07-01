package bowling.domain.frame;

import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import bowling.domain.state.StateExpression;
import bowling.fixture.FinalFrameFixture;
import bowling.fixture.FramesFixture;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class FinalFrameTest {

    @DisplayName("생성 성공")
    @Test
    public void ofFirst() {
        assertThatCode(FinalFrame::newInstance)
                .doesNotThrowAnyException();
    }

    @DisplayName("자신의 프레임 번호 반환 : 항상 MAX_NUMBER")
    @Test
    public void getNo() {
        assertThat(FinalFrame.newInstance().getFrameNo())
                .isEqualTo(FrameNumber.MAX_NUMBER);
    }

    @DisplayName("현재 상태가 종료 상태의 경우, 상태값을 추가")
    @Test
    public void finishedAfterBowl() {
        Frame nextFrame = FinalFrame.newInstance();
        nextFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        assertThat(nextFrame.getFrameResult().getSymbol())
                .isEqualTo(StateExpression.STRIKE);
    }

    @DisplayName("현재 상태가 진행 상태의 경우, 현재 상태를 제거하고 새로 던진 볼링공의 상태를 추가 및 상태값 반환")
    @ParameterizedTest
    @MethodSource
    public void runningAfterBowl(final Frame nextFrame, final String expected) {
        assertThat(nextFrame.getFrameResult().getSymbol())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> runningAfterBowl() {
        return Stream.of(
                Arguments.of(FinalFrameFixture.getOneStrikeFrame(), "X"),
                Arguments.of(FinalFrameFixture.getStrikeGutterFrame(), "X|-"),
                Arguments.of(FinalFrameFixture.getHitSpareStrikeFrame(), "2|/|X")
        );
    }

    @DisplayName("게임 종료 여부 판단 상태")
    @ParameterizedTest
    @MethodSource
    public void isGameOver(final Frame frame) {
        assertThat(frame.isGameOver())
                .isTrue();
    }

    private static Stream<Arguments> isGameOver() {
        return Stream.of(
                Arguments.of(FinalFrameFixture.getThreeStrikeFrame()),
                Arguments.of(FinalFrameFixture.getHitSpareStrikeFrame()),
                Arguments.of(FinalFrameFixture.getHitSpareHitFrame()),
                Arguments.of(FinalFrameFixture.getHitMissFrame()),
                Arguments.of(FinalFrameFixture.getStrikeHitMissFrame())
        );
    }

    @DisplayName("해당 프레임의 점수를 반환")
    @ParameterizedTest
    @MethodSource
    public void getScore(final Frames frames, final Score expected1, final Score expected2) {

        assertThat(frames.getFrames().get(0).getScore())
                .isEqualTo(expected1);
        assertThat(frames.getFrames().get(1).getScore())
                .isEqualTo(expected2);
    }

    private static Stream<Arguments> getScore() {
        return Stream.of(
                Arguments.of(FramesFixture.getSpareMissFrames(), Score.valueOf(14, 0), Score.ofMiss(8)),
                Arguments.of(FramesFixture.getSpareFrames(), Score.ofSpare(), Score.UN_SCORE),
                Arguments.of(FramesFixture.getTwoStrikeFrames(), Score.valueOf(20, 1), Score.ofStrike()),
                Arguments.of(FramesFixture.getStrikeHitFrames(), Score.valueOf(19, 1), Score.UN_SCORE),
                Arguments.of(FramesFixture.getTwoMissFrames(), Score.ofMiss(5), Score.ofMiss(8))
        );
    }

    @DisplayName("여분의 보너스 점수를 해결하기 위한 추가적인 점수를 계산하여 반환")
    @ParameterizedTest
    @MethodSource
    public void calculateAdditionalScore(final Frame frame, final Score beforeScore, final Score expected) {

        assertThat(frame.addBonusScore(beforeScore))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> calculateAdditionalScore() {
        return Stream.of(
                // X -> 5|1
                Arguments.of(FinalFrameFixture.getHitMissFrame(), Score.ofStrike(), Score.ofMiss(16)),
                // 2|3 -> 2|/|X
                Arguments.of(FinalFrameFixture.getHitSpareStrikeFrame(), Score.ofMiss(5), Score.ofMiss(5)),
                // X -> X|X|X
                Arguments.of(FinalFrameFixture.getThreeStrikeFrame(), Score.ofStrike(), Score.valueOf(30, 0)),
                // 2|/ -> X|X|2
                Arguments.of(FinalFrameFixture.getTwoStrikeOneHitFrame(), Score.ofMiss(5), Score.ofMiss(5)),
                // X -> 2|/|5
                Arguments.of(FinalFrameFixture.getHitSpareHitFrame(), Score.ofStrike(), Score.valueOf(20, 0)),
                // 2|/ -> X|2|2
                Arguments.of(FinalFrameFixture.getStrikeHitMissFrame(), Score.ofSpare(), Score.valueOf(20, 0)),
                // X -> X|1|/ (10 + 11, 10 + 1 + 9)
                Arguments.of(FinalFrameFixture.getStrikeHitSpareFrame(), Score.ofMiss(5), Score.ofMiss(5))
        );
    }
}
