package bowling.domain.frame;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:9", "5:3", "6:2"}, delimiter = ':')
    void canThrowTwoBalls(int prePoint, int curPoint) {
        finalFrame.throwBall(prePoint);
        finalFrame.throwBall(curPoint);
        assertThat(finalFrame.ended()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:9", "0:10", "5:5", "6:4"}, delimiter = ':')
    void canThrowBonusBall(int prePoint, int curPoint) {
        finalFrame.throwBall(prePoint);
        finalFrame.throwBall(curPoint);
        assertThat(finalFrame.ended()).isFalse();

        finalFrame.throwBall(prePoint);
        assertThat(finalFrame.ended()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"9:2", "8:4", "10:8", "4:7", "9:4", "8:3"}, delimiter = ':')
    @DisplayName("두 점수의 합이 10을 넘으면 INVALID_POINT_SUM을 던짐")
    void secondPointNotInRangeThrowsException(int curPoint, int prePoint) {
        finalFrame.throwBall(prePoint);
        CustomException customException = assertThrows(CustomException.class, () -> finalFrame.throwBall(curPoint));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT_SUM);
    }

    @Test
    void canThrowBonusBalls() {
        SoftAssertions softAssertions = new SoftAssertions();

        finalFrame.throwBall(10);
        softAssertions.assertThat(finalFrame.ended()).isFalse();

        finalFrame.throwBall(10);
        softAssertions.assertThat(finalFrame.ended()).isFalse();

        finalFrame.throwBall(10);
        softAssertions.assertThat(finalFrame.ended()).isTrue();

        softAssertions.assertAll();
    }

}
