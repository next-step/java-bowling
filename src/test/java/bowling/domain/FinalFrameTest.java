package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FinalFrameTest {

    @DisplayName("초기 두 번이 strike면 3번 투구 가능")
    @Test
    void doubleStrikeCase() {
        FinalFrame frame = new FinalFrame();
        frame.swing(10);
        frame.swing(10);
        assertFalse(frame.isEndedFrame());
    }

    @DisplayName("두 번째 투구가 스페어면 1번 더 투구 가능")
    @CsvSource(value = {"1,9", "2,8", "3,7"})
    @ParameterizedTest
    void secondSwingIsSpareCase(int score1, int score2) {
        FinalFrame frame = new FinalFrame();
        frame.swing(score1);
        frame.swing(score2);
        assertFalse(frame.isEndedFrame());
    }

    @DisplayName("1번만 투구했다면 무조건 더 투구가 가능")
    @RepeatedTest(value = 10)
    void secondSwing() {
        Random random = new Random();
        int score = random.nextInt(10);

        FinalFrame frame = new FinalFrame();
        frame.swing(score);
        assertFalse(frame.isEndedFrame());
    }
}
