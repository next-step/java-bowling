package bowling.domain.frame;

import bowling.domain.Swing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LastFrameTest {

    @DisplayName("첫 투구는 strike, 두번째 투구가 10점 미만이면 추가 투구 가능")
    @Test
    void firstSwingStrikeCase() {
        LastFrame frame = new LastFrame();
        frame.swing(10);

        Random random = new Random();
        int next = random.nextInt(10);
        frame.swing(next);
        assertFalse(frame.isEndedFrame());
    }

    @DisplayName("두 번 strike는 추가 투구 가능")
    @Test
    void doubleStrikeCase() {
        LastFrame frame = new LastFrame();
        frame.swing(10);
        frame.swing(10);
        assertFalse(frame.isEndedFrame());
    }

    @DisplayName("스페어는 추가 투구 가능")
    @CsvSource(value = {"1,9", "2,8", "3,7"})
    @ParameterizedTest
    void secondSwingIsSpareCase(int score1, int score2) {
        LastFrame frame = new LastFrame();
        frame.swing(score1);
        frame.swing(score2);
        assertFalse(frame.isEndedFrame());
    }

    @DisplayName("마지막 프레임에서 모두 투구되지 않으면 점수는 계산되지 않음")
    @Test
    void lastFrameScoreDummyValueTest() {
        LastFrame frame = new LastFrame();
        Random r = new Random();

        while (!frame.isEndedFrame()) {
            Assertions.assertEquals(frame.getScore(), Swing.DUMMY_SCORE);
            frame.swing(r.nextInt(11));
        }
    }

    @DisplayName("마지막 프레임 점수 계산")
    @MethodSource("scoreArguments")
    @ParameterizedTest
    void lastFrameScoreTest(int argumentsSum, String scoresStr) {

        int[] scores = Arrays.stream(scoresStr.split(","))
                             .mapToInt(Integer::valueOf)
                             .toArray();

        int sum = 0;

        LastFrame frame = new LastFrame();

        for (int i = 0; !frame.isEndedFrame(); i++) {
            sum += scores[i];
            frame.swing(scores[i]);
        }

        assertEquals(sum, argumentsSum);
    }

    private static Stream<Arguments> scoreArguments() {
        return Stream.of(
                Arguments.of(20, "10,0,10"),
                Arguments.of(30, "10,10,10"),
                Arguments.of(20, "1,9,10"),
                Arguments.of(15, "5,5,5"),
                Arguments.of(6, "1,5")
        );
    }
}
