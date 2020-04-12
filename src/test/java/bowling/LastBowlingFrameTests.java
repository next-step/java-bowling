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

@DisplayName("LastBowlingFrame 테스트")
public class LastBowlingFrameTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(LastBowlingFrame::newInstance);
    }

    @DisplayName("첫번째 투구 테스트")
    @Test
    public void addFirstBowlTest() {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        assertThatCode(() -> bowlingFrame.bowl(5));
    }

    @DisplayName("두번째 투구 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,4", "6,4"})
    public void addSecondBowlTest(final int firstBowl, final int secondBowl) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        bowlingFrame.bowl(firstBowl);
        assertThatCode(() -> bowlingFrame.bowl(secondBowl));
    }

    @DisplayName("두번째 투구 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "9,2"})
    public void addSecondBowlAbnormalTest(final int firstBowl, final int secondBowl) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        bowlingFrame.bowl(firstBowl);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> bowlingFrame.bowl(secondBowl));
    }

    @DisplayName("세번째 투구 추가 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,3,7", "8,2,3", "0,10,2"})
    public void addThirdBowlTest(final int firstPitch, final int secondPitch, final int thirdPitch) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        bowlingFrame.bowl(firstPitch);
        bowlingFrame.bowl(secondPitch);
        assertThatCode(() -> bowlingFrame.bowl(thirdPitch));
    }

    @DisplayName("BowlingFrame 세번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,3,8", "8,1,3", "0,0,2"})
    public void addThirdBowlAbnormalTest(final int firstPitch, final int secondPitch, final int thirdPitch) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        bowlingFrame.bowl(firstPitch);
        bowlingFrame.bowl(secondPitch);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> bowlingFrame.bowl(thirdPitch));
    }

    @DisplayName("합 테스트")
    @Test
    public void sumTest() {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        bowlingFrame.bowl(5);
        bowlingFrame.bowl(5);
        bowlingFrame.bowl(6);
        assertThat(bowlingFrame.sum()).isEqualTo(16);
    }

    @DisplayName("종료 테스트")
    @ParameterizedTest
    @MethodSource("overTestCases")
    public void overTest(List<Integer> scores, boolean expectedResult) {
        LastBowlingFrame bowlingFrame = LastBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> overTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(10), false),
                Arguments.of(Arrays.asList(5, 5), false),
                Arguments.of(Arrays.asList(10, 5), false),
                Arguments.of(Arrays.asList(5, 5, 5), true)
        );
    }
}
