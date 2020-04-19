package bowling.frame;

import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName(" Tests")
public class BowlingFramesTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(BowlingFrames::newInstance).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        assertThatCode(() -> bowlingFrames.bowl(Pin.ofMax())).doesNotThrowAnyException();
    }

    @DisplayName("게임 종료 테스트")
    @Test
    public void overTest() {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        IntStream.range(0, 10)
                .forEach(i -> bowlingFrames.bowl(Pin.ofMax()));

        assertFalse(bowlingFrames.isAllFrameOver());

        bowlingFrames.bowl(Pin.ofMax());
        assertTrue(bowlingFrames.isAllFrameOver());
    }

    @DisplayName("프레임 갯수 테스트")
    @Test
    public void sizeTest() {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        assertThat(bowlingFrames.size()).isEqualTo(1);

        IntStream.range(0, 10)
                .forEach(i -> bowlingFrames.bowl(Pin.ofMax()));
        assertThat(bowlingFrames.size()).isEqualTo(10);
    }

    @DisplayName("합산 점수 테스트")
    @ParameterizedTest
    @MethodSource("totalScoreTestCases")
    public void totalScoreTest(final List<Pin> pins, List<Score> expectedScore) {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        pins.forEach(bowlingFrames::bowl);
        assertThat(bowlingFrames.getTotalScores()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> totalScoreTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pin.ofMax()), Collections.singletonList(Score.ofAllPins())),
                Arguments.of(Arrays.asList(Pin.ofMax(), Pin.of(5), Pin.of(3)), Arrays.asList(Score.of(18), Score.of(26))),
                Arguments.of(Arrays.asList(Pin.ofMax(), Pin.of(7), Pin.of(3)), Arrays.asList(Score.of(20), Score.of(30))),
                Arguments.of(Arrays.asList(Pin.ofMax(), Pin.ofMax(), Pin.ofMax()), Arrays.asList(Score.of(30), Score.of(20), Score.of(10)))
        );
    }
}
