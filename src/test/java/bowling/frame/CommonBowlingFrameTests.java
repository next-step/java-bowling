package bowling.frame;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("볼링 프레임 테스트")
public class CommonBowlingFrameTests {

    @DisplayName("생성 테스트 - 첫 프레임, 1~9 프레임, 마지막 프레임")
    @Test
    public void generateTest() {
        assertThatCode(BowlingFrame::createFirstFrame);
        assertThatCode(() -> BowlingFrame.newInstance(5));
    }

    @DisplayName("투구 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void bowlTest(final int pinCount) {
        BowlingFrame bowlingFrame = BowlingFrame.createFirstFrame();
        assertThatCode(() -> bowlingFrame.bowl(Pin.of(pinCount)));
    }

    @DisplayName("합산 스코어 테스트")
    @Test
    public void getTotalScoreTest() {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        bowlingFrame.bowl(Pin.of(5));
        assertThat(bowlingFrame.getTotalScore(Score.ofAllPins())).isEqualTo(Score.of(15));
    }

    @DisplayName("일반 프레임 추가 테스트")
    @Test
    public void appendNextFrameTest() {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        assertThatCode(() -> bowlingFrame.appendNextFrame(5));
    }

    @DisplayName("일반 프레임 종료 테스트")
    @ParameterizedTest
    @MethodSource("commonFrameOverTestCases")
    public void commonFrameOverTest(List<Pin> scores, boolean expectedResult) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> commonFrameOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, false),
                Arguments.of(Collections.singletonList(Pin.of(5)), false),
                Arguments.of(Collections.singletonList(Pin.of(10)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), true)
        );
    }

    @DisplayName("일반 프레임 계산 가능 테스트")
    @ParameterizedTest
    @MethodSource("commonFrameCanCalculateTestCases")
    public void commonFrameCanCalculateTest(List<Pin> scores, boolean expectedResult) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.canCalculateScore()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> commonFrameCanCalculateTestCases() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, false),
                Arguments.of(Collections.singletonList(Pin.of(5)), false),
                Arguments.of(Collections.singletonList(Pin.of(10)), false),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), false)
        );
    }

    @DisplayName("일반 프레임 계산 가능 테스트2")
    @ParameterizedTest
    @MethodSource("commonFrameCanCalculateTestCases2")
    public void commonFrameCanCalculateTest2(List<Pin> scores, FrameScore beforeFrameScore, boolean expectedResult) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.canCalculateWithBeforeScore(beforeFrameScore)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> commonFrameCanCalculateTestCases2() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), false),
                Arguments.of(Collections.singletonList(Pin.of(5)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(1)), true),
                Arguments.of(Collections.singletonList(Pin.of(10)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), false),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), true)
        );
    }
}
