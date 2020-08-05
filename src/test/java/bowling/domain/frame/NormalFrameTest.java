package bowling.domain.frame;

import bowling.common.exception.InvalidThrowBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NormalFrameTest {
    @Test
    @DisplayName("1~9 프레임은 스트라이크 후에 투구하지 않는다.")
    public void getStrikeFrame() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(10);
        assertThat(normalFrame.isRollingPossible()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    @DisplayName("1~9 프레임은 스트라이크 외엔 2번째 투구를 한다.")
    public void getNotStrikeFrame(int pinCount) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(pinCount);
        assertThat(normalFrame.isRollingPossible()).isTrue();
    }

    @Test
    @DisplayName("세번째 투구 시도 불가 확인")
    public void throwBallThirdTimesNotRollable() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(2);
        normalFrame.rollingBall(2);
        assertThat(normalFrame.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("세번째 투구 시도 시 Exception 발생")
    public void throwBallThirdTimesException() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(2);
        normalFrame.rollingBall(2);

        assertThatExceptionOfType(InvalidThrowBallException.class).isThrownBy(
                () -> normalFrame.rollingBall(2)
        );
    }

    @Test
    @DisplayName("Strike 후 점수 계산")
    public void calculateScoreOfStrikeFrame() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(10);

        normalFrame.calculateScore(null);
        assertThat(normalFrame.getFrameScore()).isEqualTo(10);
        assertThat(normalFrame.isScoreCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Spare 후 점수 계산")
    public void calculateScoreOfSpareFrame() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(4);
        normalFrame.rollingBall(6);

        normalFrame.calculateScore(null);
        assertThat(normalFrame.getFrameScore()).isEqualTo(10);
        assertThat(normalFrame.isScoreCalculateDone()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "0,8", "3,5", "9,0"})
    @DisplayName("Miss/Gutter 점수 계산")
    public void calculateScoreOfMissFrame(int firstPinCount, int secondPinCount) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(firstPinCount);
        normalFrame.rollingBall(secondPinCount);
        normalFrame.calculateScore(null);

        assertThat(normalFrame.getFrameScore()).isEqualTo(firstPinCount + secondPinCount);
        assertThat(normalFrame.isScoreCalculateDone()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "2,8", "3,5", "10,10"})
    @DisplayName("Strike 후 추가 점수 계산")
    public void calculateScoreOfStrikeFrameWithAdditionalRollings(int firstPinCount, int secondPinCount) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(10);
        normalFrame.calculateScore(null);

        normalFrame.addScore(firstPinCount);
        assertThat(normalFrame.getFrameScore()).isEqualTo(10 + firstPinCount);
        assertThat(normalFrame.isScoreCalculateDone()).isFalse();

        normalFrame.addScore(secondPinCount);
        assertThat(normalFrame.getFrameScore()).isEqualTo(10 + firstPinCount + secondPinCount);
        assertThat(normalFrame.isScoreCalculateDone()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 9, 10})
    @DisplayName("Spare 후 점수 계산")
    public void calculateScoreOfSpareFrameWithAdditionalRollings(int additionalPinCount) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(4);
        normalFrame.rollingBall(6);
        normalFrame.calculateScore(null);

        normalFrame.addScore(additionalPinCount);
        assertThat(normalFrame.getFrameScore()).isEqualTo(10 + additionalPinCount);
        assertThat(normalFrame.isScoreCalculateDone()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0,3", "0,8,5", "3,5,10"})
    @DisplayName("이전 점수 기반 점수 계산")
    public void calculateScoreWithLastFrame(int firstPinCount, int secondPinCount, int currentPinCount) {
        NormalFrame lastFrame = new NormalFrame();
        lastFrame.rollingBall(firstPinCount);
        lastFrame.rollingBall(secondPinCount);
        lastFrame.calculateScore(null);

        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(currentPinCount);
        normalFrame.calculateScore(lastFrame);

        assertThat(normalFrame.getFrameScore()).isEqualTo(firstPinCount + secondPinCount + currentPinCount);
    }
}
