package bowling.domain.frame;

import bowling.common.exception.InvalidThrowBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {
    @Test
    @DisplayName("스트라이크 후에 보너스 투구")
    public void getStrikeFrame() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(10);
        assertThat(finalFrame.isRollingPossible()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    @DisplayName("스트라이크 외엔 2번째 투구 진행")
    public void getNotStrikeFrame(int pinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(pinCount);
        assertThat(finalFrame.isRollingPossible()).isTrue();
    }

    @Test
    @DisplayName("스페어 후 보너스 투구")
    public void throwBallThirdTimesRollingPossible() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(2);
        finalFrame.rollingBall(8);
        assertThat(finalFrame.isRollingPossible()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 7})
    @DisplayName("보너스 투구가 아닌 세번째 투구 시도 시 Exception 발생")
    public void throwBallThirdTimesException(int pinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(2);
        finalFrame.rollingBall(pinCount);

        assertThatExceptionOfType(InvalidThrowBallException.class).isThrownBy(
                () -> finalFrame.rollingBall(2)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"10,0", "10,2", "10,10"})
    @DisplayName("Strike 후 보너스 투구까지 점수 계산")
    public void calculateScoreOfStrikeFrameWithBonus(int firstPinCount, int secondPinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(firstPinCount);
        finalFrame.rollingBall(secondPinCount);

        finalFrame.calculateScore(null);
        assertThat(finalFrame.getFrameScore()).isEqualTo(firstPinCount + secondPinCount);
    }

    @Test
    @DisplayName("Spare 후 보너스 투구까지 점수 계산")
    public void calculateScoreOfSpareFrameWithBonus() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(4);
        finalFrame.rollingBall(6);
        finalFrame.rollingBall(5);

        finalFrame.calculateScore(null);
        assertThat(finalFrame.getFrameScore()).isEqualTo(15);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "0,8", "3,5", "9,0"})
    @DisplayName("Miss/Gutter 점수 계산")
    public void calculateScoreOfMissFrame(int firstPinCount, int secondPinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(firstPinCount);
        finalFrame.rollingBall(secondPinCount);

        finalFrame.calculateScore(null);
        assertThat(finalFrame.getFrameScore()).isEqualTo(firstPinCount + secondPinCount);
    }
}
