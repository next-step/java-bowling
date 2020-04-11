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

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(FrameScore::new);
    }

    @DisplayName("FrameScore 첫번째 투구 추가 테스트")
    @Test
    public void addFirstPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        assertThatCode(() -> frameScore.pitch(5, false));
    }

    @DisplayName("FrameScore 두번째 투구 추가 테스트")
    @Test
    public void addSecondPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(5, false);
        assertThatCode(() -> frameScore.pitch(4, false));
    }

    @DisplayName("FrameScore 두번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "10,4"})
    public void addSecondPitchFrameScoreAbnormalTest(final int firstPitch, final int secondPitch) {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(firstPitch, false);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameScore.pitch(secondPitch, false));
    }

    @DisplayName("FrameScore 세번째 투구 추가 테스트")
    @Test
    public void addThirdPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(5, true);
        frameScore.pitch(5, true);
        assertThatCode(() -> frameScore.pitch(4, true));
    }

    @DisplayName("FrameScore 세번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,3,8", "8,1,3"})
    public void addThirdPitchFrameScoreAbnormalTest(final int firstPitch, final int secondPitch, final int thirdPitch) {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(firstPitch, true);
        frameScore.pitch(secondPitch, true);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameScore.pitch(thirdPitch, true));
    }

    @DisplayName("FrameScore 합 테스트")
    @Test
    public void generateFrameScoreSumTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(5, 4));
        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("FrameScore 종료 테스트")
    @ParameterizedTest
    @MethodSource("generateFrameScoreOverTestCases")
    public void generateFrameScoreOverTest(List<Integer> scores, boolean isLastFrame, boolean expectedResult) {
        FrameScore frameScore = FrameScore.newInstance(scores);
        assertThat(frameScore.isOver(isLastFrame)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateFrameScoreOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(5), false, false),
                Arguments.of(Collections.singletonList(10), false, true),
                Arguments.of(Collections.singletonList(10), true, false),
                Arguments.of(Arrays.asList(5, 4), false, true),
                Arguments.of(Arrays.asList(5, 5), false, true),
                Arguments.of(Arrays.asList(5, 5), true, false),
                Arguments.of(Arrays.asList(10, 5), true, true),
                Arguments.of(Arrays.asList(5, 5, 5), true, true)
        );
    }
}
