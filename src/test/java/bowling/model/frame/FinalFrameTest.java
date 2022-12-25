package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    FinalFrame finalFrame;

    @BeforeEach
    void init() {
        finalFrame = new FinalFrame(10);
    }

    @DisplayName("게임 진행 중")
    @ParameterizedTest
    @CsvSource(value = {"10,10", "0,10", "10,0"})
    void isFinishedFalse(int first, int second) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("3번 쳤을 때 게임 끝인 경우")
    @ParameterizedTest
    @CsvSource(value = {"10,10,10", "0,10,10", "10,0,10", "10,3,3"})
    void isFinishedTrue1(int first, int second, int third) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));
        finalFrame.bowl(Pin.of(third));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("2번 쳤을 때 게임 끝인 경우")
    @ParameterizedTest
    @CsvSource(value = {"0,5"})
    void isFinishedTrue2(int first, int second) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("3번 쳤을 때 점수 계산 가능한 경우")
    @ParameterizedTest
    @CsvSource(value = {"10,10,10,30", "0,10,10,20", "10,0,10,20", "10,3,3,16"})
    void getScore(int first, int second, int third, int value) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));
        finalFrame.bowl(Pin.of(third));

        Score score = finalFrame.getScore();
        assertThat(score.canCalculate()).isTrue();
        assertThat(score).isEqualTo(new Score(value, 0));
    }

    @DisplayName("2번 쳤을 때 점수 계산 가능한 경우")
    @ParameterizedTest
    @CsvSource(value = {"0,5,5"})
    void getScore2(int first, int second, int value) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        Score score = finalFrame.getScore();
        assertThat(score.canCalculate()).isTrue();
        assertThat(score).isEqualTo(new Score(value, 0));
    }

    @DisplayName("2번 쳤을 때 점수 계산 불가능한 경우")
    @ParameterizedTest
    @CsvSource(value = {"10,10", "0,10", "10,0", "10,3"})
    void getScoreImpossible(int first, int second) {
        finalFrame.bowl(Pin.of(first));
        finalFrame.bowl(Pin.of(second));

        Score score = finalFrame.getScore();
        assertThat(score.canCalculate()).isFalse();
    }

    @DisplayName("보너스 점수 계산 가능한 경우")
    @Test
    void addBonusScorePossible() {
        finalFrame.bowl(Pin.of(8));
        Score score = finalFrame.addBonusScore(new Score(10, 1));

        assertThat(score.canCalculate()).isTrue();
        assertThat(score).isEqualTo(new Score(18, 0));
    }

    @DisplayName("보너스 점수 계산 불가능한 경우")
    @Test
    void addBonusScoreImPossible() {
        Score score = finalFrame.addBonusScore(new Score(10, 2));
        assertThat(score.canCalculate()).isFalse();
    }
}
