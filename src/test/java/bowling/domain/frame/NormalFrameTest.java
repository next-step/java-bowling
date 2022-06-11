package bowling.domain.frame;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    private Frame normalFrame;

    @BeforeEach
    void setup() {
        normalFrame = NormalFrame.initialize();
    }

    @ParameterizedTest(name = "{0} Frame: NormalFrame")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void nextFrame(int number) throws Exception {
        for (int i = 1; i < number; i++) {
            normalFrame.bowling(10);
            normalFrame = normalFrame.next();
        }

        assertThat(normalFrame.frameNo().toInt()).isEqualTo(number);
        assertThat(normalFrame).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("1차 투구에서 모든 Pin 을 쓰러트리면 현재 프레임은 종료한다.")
    void finishFrame_strike() {
        normalFrame.bowling(10);
        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("2차 투구까지 완료하면 현재 프레임은 종료한다.")
    void finishFrame() {
        normalFrame.bowling(5);
        normalFrame.bowling(2);

        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("Strike 의 경우 이후 2번의 투구점수(프레임 상관없이 투구횟수만)를 합산한다.")
    void score_strike() throws Exception {
        normalFrame.bowling(10);
        Frame nextFrame = normalFrame.next();
        nextFrame.bowling(5);
        nextFrame.bowling(5);

        assertThat(normalFrame.score()).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("Strike 의 경우 이후 2번의 투구점수(프레임 상관없이 투구횟수만)를 합산한다.")
    void score_strike_2() throws Exception {
        normalFrame.bowling(10);
        Frame nextFrame = normalFrame.next();
        nextFrame.bowling(10);
        Frame nextFrame2 = nextFrame.next();
        nextFrame2.bowling(3);
        nextFrame2.bowling(5);

        assertThat(normalFrame.score()).isEqualTo(Score.of(23, 0));
    }

    @Test
    @DisplayName("Spare 의 경우 이후 1번의 투구점수를 합산한다.")
    void score_spare() throws Exception {
        normalFrame.bowling(3);
        normalFrame.bowling(7);
        Frame nextFrame = normalFrame.next();
        nextFrame.bowling(5);
        nextFrame.bowling(5);

        assertThat(normalFrame.score()).isEqualTo(Score.of(15, 0));
    }

    @Test
    @DisplayName("Miss 의 경우 프레임의 점수는 {firstHit} + {secondHit} 이다.")
    void score_miss() {
        normalFrame.bowling(3);
        normalFrame.bowling(2);

        assertThat(normalFrame.score()).isEqualTo(Score.of(5, 0));
    }

    @Test
    @DisplayName("프레임이 종료되지 않을 경우 예외를 반환한다.")
    void score_empty() {
        normalFrame.bowling(5);

        assertThatThrownBy(() -> normalFrame.score()).isInstanceOf(CannotCalculateScore.class);
    }
}
