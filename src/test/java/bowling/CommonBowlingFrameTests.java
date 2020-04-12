package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("CommonBowlingFrame 테스트")
public class CommonBowlingFrameTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(CommonBowlingFrame::newInstance);
    }

    @DisplayName("첫번째 투구 테스트")
    @Test
    public void addFirstBowlTest() {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        assertThatCode(() -> bowlingFrame.bowl(5));
    }

    @DisplayName("두번째 투구 테스트")
    @Test
    public void addSecondBowlTest() {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        bowlingFrame.bowl(5);
        assertThatCode(() -> bowlingFrame.bowl(4));
    }

    @DisplayName("두번째 투구 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "10,4"})
    public void addSecondBowlAbnormalTest(final int firstBowl, final int secondBowl) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        bowlingFrame.bowl(firstBowl);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> bowlingFrame.bowl(secondBowl));
    }

    @DisplayName("합 테스트")
    @Test
    public void sumTest() {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        bowlingFrame.bowl(5);
        bowlingFrame.bowl(4);
        assertThat(bowlingFrame.sum()).isEqualTo(9);
    }

    @DisplayName("종료 테스트")
    @ParameterizedTest
    @MethodSource("overTestCases")
    public void overTest(List<Integer> scores, boolean expectedResult) {
        CommonBowlingFrame bowlingFrame = CommonBowlingFrame.newInstance();
        scores.forEach(bowlingFrame::bowl);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> overTestCases() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST, false),
                Arguments.of(Collections.singletonList(5), false),
                Arguments.of(Collections.singletonList(10), true),
                Arguments.of(Arrays.asList(5, 4), true),
                Arguments.of(Arrays.asList(5, 5), true)
        );
    }
}
