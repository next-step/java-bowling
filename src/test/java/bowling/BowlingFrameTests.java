package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("BowlingFrame 테스트")
public class BowlingFrameTests {

    @DisplayName("BowlingFrame 생성 테스트")
    @Test
    public void generateBowlingFrameTest() {
        assertThatCode(() -> BowlingFrame.newInstance(false));
        assertThatCode(() -> BowlingFrame.newInstance(true));
    }

    @DisplayName("BowlingFrame 첫번째 투구 추가 테스트")
    @Test
    public void addFirstPitchBowlingFrameTest() {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(false);
        assertThatCode(() -> bowlingFrame.pitch(5));
    }

    @DisplayName("BowlingFrame 두번째 투구 추가 테스트 - 1~9 프레임")
    @Test
    public void addSecondPitchFrameScoreTest() {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(false);
        bowlingFrame.pitch(5);
        assertThatCode(() -> bowlingFrame.pitch(4));
    }

    @DisplayName("BowlingFrame 두번째 투구 추가 테스트 - 10 프레임")
    @Test
    public void addSecondPitchFrameScoreLastFrameTest() {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(true);
        bowlingFrame.pitch(10);
        assertThatCode(() -> bowlingFrame.pitch(4));
    }

    @DisplayName("BowlingFrame 두번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "10,4"})
    public void addSecondPitchBowlingFrameAbnormalTest(final int firstPitch, final int secondPitch) {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(false);
        bowlingFrame.pitch(firstPitch);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> bowlingFrame.pitch(secondPitch));
    }

    @DisplayName("BowlingFrame 세번째 투구 추가 테스트")
    @Test
    public void addThirdPitchBowlingFrameTest() {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(true);
        bowlingFrame.pitch(5);
        bowlingFrame.pitch(5);
        assertThatCode(() -> bowlingFrame.pitch(4));
    }

    @DisplayName("BowlingFrame 세번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,3,8", "8,1,3"})
    public void addThirdPitchBowlingFrameAbnormalTest(final int firstPitch, final int secondPitch, final int thirdPitch) {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(true);
        bowlingFrame.pitch(firstPitch);
        bowlingFrame.pitch(secondPitch);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> bowlingFrame.pitch(thirdPitch));
    }

    @DisplayName("BowlingFrame 합 테스트")
    @Test
    public void generateBowlingFrameSumTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(5, 4));
        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("BowlingFrame 종료 테스트")
    @ParameterizedTest
    @MethodSource("generateBowlingFrameOverTestCases")
    public void generateBowlingFrameOverTest(List<Integer> scores, boolean isLastFrame, boolean expectedResult) {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(scores, isLastFrame);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateBowlingFrameOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(5), false, false),
                Arguments.of(Collections.singletonList(10), false, true),
                Arguments.of(Collections.singletonList(10), true, false),
                Arguments.of(Arrays.asList(5, 4), false, true),
                Arguments.of(Arrays.asList(5, 5), false, true),
                Arguments.of(Arrays.asList(5, 5), true, false),
                Arguments.of(Arrays.asList(10, 5), true, false),
                Arguments.of(Arrays.asList(5, 5, 5), true, true)
        );
    }
}
