package bowling.domain.frame;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameTest {

    private Frame frame;

    @BeforeEach
    void setup() {
        this.frame = new Frame();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:9", "5:3", "6:2"}, delimiter = ':')
    void canThrowTwoBalls(int prePoint, int curPoint) {
        frame.throwBall(prePoint);
        frame.throwBall(curPoint);
        assertThat(frame.ended()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:9", "0:10", "5:5", "6:4"}, delimiter = ':')
    void canTellSpare(int prePoint, int curPoint) {
        frame.throwBall(prePoint);
        frame.throwBall(curPoint);
        assertThat(frame.spared()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"9:2", "8:4", "10:8", "4:7", "9:4", "8:3"}, delimiter = ':')
    @DisplayName("두 점수의 합이 10을 넘으면 INVALID_POINT_SUM을 던짐")
    void secondPointNotInRangeThrowsException(int curPoint, int prePoint) {
        frame.throwBall(prePoint);
        CustomException customException = assertThrows(CustomException.class, () -> frame.throwBall(curPoint));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT_SUM);
    }
}
