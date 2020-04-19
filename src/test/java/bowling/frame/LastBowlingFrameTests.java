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

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임 테스트")
public class LastBowlingFrameTests {


    @DisplayName("생성 테스트 마지막 프레임")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingFrame.newInstance(10));
        assertThatCode(() -> LastBowlingFrame.newInstance());
    }

    @DisplayName("투구 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void bowlTest(final int pinCount) {
        LastBowlingFrame lastFrame = LastBowlingFrame.newInstance();
        assertThatCode(() -> lastFrame.bowl(Pin.of(pinCount)));
    }

    @DisplayName("합산 스코어 테스트")
    @Test
    public void getTotalScoreTest() {
        LastBowlingFrame lastFrame = LastBowlingFrame.newInstance();
        lastFrame.bowl(Pin.of(10));
        assertThat(lastFrame.getTotalScore(Score.ofZeroPins())).isEqualTo(Score.of(10));
    }

    @DisplayName("마지막 프레임 추가 테스트")
    @Test
    public void appendNextFrameTest() {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingFrame.appendNextFrame(10));
    }

    @DisplayName("마지막 프레임 종료 테스트")
    @ParameterizedTest
    @MethodSource("lastFrameOverTestCases")
    public void lastFrameOverTest(List<Pin> scores, boolean expectedResult) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> lastFrameOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, false),
                Arguments.of(Collections.singletonList(Pin.of(5)), false),
                Arguments.of(Collections.singletonList(Pin.of(10)), false),
                Arguments.of(Arrays.asList(Pin.of(10), Pin.of(5)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), false),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5), Pin.of(4)), true)
        );
    }

    @DisplayName("마지막 프레임 계산 가능 테스트")
    @ParameterizedTest
    @MethodSource("lastFrameCanCalculateTestCases")
    public void lastFrameCanCalculateTest(List<Pin> scores, boolean expectedResult) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.canCalculateScore()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> lastFrameCanCalculateTestCases() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, false),
                Arguments.of(Collections.singletonList(Pin.of(5)), false),
                Arguments.of(Collections.singletonList(Pin.of(10)), false),
                Arguments.of(Arrays.asList(Pin.of(10), Pin.of(4)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), false),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5), Pin.of(5)), true)
        );
    }

    @DisplayName("마지막 프레임 계산 가능 테스트2")
    @ParameterizedTest
    @MethodSource("lastFrameCanCalculateTestCases2")
    public void lastFrameCanCalculateTest2(List<Pin> scores, FrameScore beforeFrameScore, boolean expectedResult) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.canCalculateScore(beforeFrameScore)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> lastFrameCanCalculateTestCases2() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), false),
                Arguments.of(Collections.singletonList(Pin.of(5)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(1)), true),
                Arguments.of(Collections.singletonList(Pin.of(10)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), false),
                Arguments.of(Arrays.asList(Pin.of(10), Pin.of(4)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(4)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(2)), true),
                Arguments.of(Arrays.asList(Pin.of(5), Pin.of(5), Pin.of(5)), FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(1)), true)
        );
    }

}
