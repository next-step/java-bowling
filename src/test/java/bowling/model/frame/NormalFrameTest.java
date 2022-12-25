package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.Score;
import bowling.model.state.First;
import bowling.model.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    NormalFrame normalFrame;

    @BeforeEach
    void init() {
        normalFrame = NormalFrame.first();
    }

    @Test
    @DisplayName("첫번째 프레임이 생성될 때 프레임 번호는 1, 상태는 레디이다.")
    void firstFrame() {
        assertThat(normalFrame.getNumber()).isEqualTo(1);
        assertThat(normalFrame.getCurrentState()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("게임이 진행되면 상태가 바뀐다.")
    void bowl() {
        normalFrame.bowl(Pin.of(5));
        assertThat(normalFrame.getCurrentState()).isInstanceOf(First.class);
    }

    @DisplayName("레디일때 게임이 끝나지 않는다.")
    @Test
    void isFinishedFalse1() {
        assertThat(normalFrame.isFinished()).isFalse();
    }

    @DisplayName("첫번째 볼일때 게임이 끝나지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void isFinishedFalse1(int input) {
        normalFrame.bowl(Pin.of(input));
        assertThat(normalFrame.isFinished()).isFalse();
    }

    @DisplayName("스페어와 미스일때 게임이 끝난다.")
    @ParameterizedTest
    @CsvSource(value = {"0,10", "3,4"})
    void isFinishedTrue1(int first, int second) {
        normalFrame.bowl(Pin.of(first));
        normalFrame.bowl(Pin.of(second));
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("스트라이크일때 게임이 끝난다.")
    void isFinishedTrue2() {
        normalFrame.bowl(Pin.of(10));
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("다음 프레임이 생성될 때 프레임 번호가 증가한다.")
    void nextFrame() {
        NormalFrame frame = (NormalFrame) normalFrame.nextFrame();
        assertThat(frame.getNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("프레임 번호가 9이면 마지막 프레임이 생성된다.")
    void lastFrame() {
        NormalFrame normalFrame = new NormalFrame(9);
        assertThat(normalFrame.nextFrame()).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("점수 계산 가능한 경우")
    @Test
    void getScorePossible() {
        normalFrame.bowl(Pin.of(8));
        normalFrame.bowl(Pin.of(1));

        Score score = normalFrame.getScore();
        assertThat(score.canCalculate()).isTrue();
        assertThat(score).isEqualTo(new Score(9, 0));
    }

    @DisplayName("점수 계산 불가능한 경우")
    @Test
    void getScoreImPossible() {
        normalFrame.bowl(Pin.of(8));
        normalFrame.bowl(Pin.of(2));
        normalFrame.nextFrame();

        Score score = normalFrame.getScore();
        assertThat(score.canCalculate()).isFalse();
    }

    @DisplayName("보너스 점수 계산 가능한 경우")
    @Test
    void addBonusScorePossible() {
        normalFrame.bowl(Pin.of(5));
        Score score = normalFrame.addBonusScore(new Score(10, 1));

        assertThat(score).isEqualTo(new Score(15, 0));
    }

    @DisplayName("보너스 점수 계산 불가능한 경우")
    @Test
    void addBonusScoreImPossible() {
        normalFrame.bowl(Pin.of(8));
        normalFrame.nextFrame();

        Score score = normalFrame.addBonusScore(new Score(10, 2));
        assertThat(score.canCalculate()).isFalse();
    }
}
