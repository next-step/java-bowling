package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;
import bowling.domain.PinsTest;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BasicFrameTest {

    @DisplayName("초기 프레임 생성")
    @Test
    void initialize() {
        // given
        BasicFrame basicFrame = BasicFrame.initialize();
        // when & then
        assertAll(
                () -> assertThat(basicFrame.getIndex()).isEqualTo(FrameIndex.MIN_INDEX),
                () -> assertThat(basicFrame.isEnd()).isFalse(),
                () -> assertThat(basicFrame.symbol()).isBlank()
        );
    }

    @DisplayName("2번 투구를 하고 난 후, 다음 프레임 변경 확인")
    @Test
    void bowlTwoPitching() {
        // given
        BasicFrame basicFrame = BasicFrame.initialize();
        // when
        Frame frameAfterTwoPitching = basicFrame.bowl(PinsTest.FOUR).bowl(PinsTest.FIVE);
        // then
        assertThat(frameAfterTwoPitching.getIndex()).isEqualTo(2);
    }

    @DisplayName("strike 이후에 다음 프레임인지 여부 확인")
    @Test
    void bowlStrike() {
        // given
        BasicFrame basicFrame = BasicFrame.initialize();
        // when & then
        assertThat(basicFrame.bowl(PinsTest.TEN)).isEqualTo(BasicFrame.create(FrameIndex.create(2)));
    }

    @DisplayName("첫 투구 후 strike가 아닌 경우 현재 프레임인지 여부 확인")
    @Test
    void bowlFirst() {
        // given
        BasicFrame basicFrame = BasicFrame.initialize();
        // when & then
        assertThat(basicFrame.bowl(Pins.create(5))).isEqualTo(basicFrame);
    }

    @DisplayName("마지막 전 프레임인 경우, 다음 프레임로 넘어가는지 여부 확인")
    @Test
    void bowlSecondBeforeLastFrame() {
        // given
        BasicFrame basicFrame = BasicFrame.create(FrameIndex.create(9), Ready.create());
        // when & then
        assertThat(basicFrame.bowl(Pins.create(10))).isInstanceOf(LastFrame.class);
    }

    @DisplayName("1 spare 1 strike 이후, Score 반환")
    @Test
    void scoreAfterSpare() {
        // given
        BasicFrame basicFrame = BasicFrame.create(FrameIndex.create(5), Strike.create());
        Score spare = Score.of(10, 1);
        // when & then
        assertThat(basicFrame.scoreAfter(spare)).isEqualTo(20);
    }

    @DisplayName("추가 점수 계산시 다음 프레임이 null 이면 -1 반환")
    @Test
    void scoreAfterStrike() {
        // given
        BasicFrame basicFrame = BasicFrame.create(FrameIndex.create(5), Strike.create());
        Score strike = Score.of(10, 2);
        // when & then
        assertThat(basicFrame.scoreAfter(strike)).isEqualTo(Score.INCOMPUTABLE_SCORE_VALUE);
    }

    @DisplayName("ready")
    @Test
    void ready() {
        verify(Collections.emptyList(), false, "", Score.INCOMPUTABLE_SCORE_VALUE);
    }

    @DisplayName("6 pins")
    @Test
    void sixPins() {
        verify(Collections.singletonList(6), false, "6", Score.INCOMPUTABLE_SCORE_VALUE);
    }

    @DisplayName("3 pins 1 gutter")
    @Test
    void threePinsOneGutter() {
        verify(Arrays.asList(3, 0), true, "3|-", 3 + 0);
    }

    @DisplayName("4 pins miss")
    @Test
    void fourPinsMiss() {
        verify(Arrays.asList(4, 3), true, "4|3", 4 + 3);
    }

    @DisplayName("5 pins spare")
    @Test
    void fivePinsSpare() {
        verify(Arrays.asList(5, 5), true, "5|/", Score.INCOMPUTABLE_SCORE_VALUE);
    }

    @DisplayName("strike")
    @Test
    void strike() {
        verify(Collections.singletonList(10), true, "X", Score.INCOMPUTABLE_SCORE_VALUE);
    }

    private void verify(List<Integer> pinNumbers, boolean end, String symbol, int score) {
        // given
        BasicFrame basicFrame = BasicFrame.create(FrameIndex.create(5), Ready.create());
        // when
        for (int pinNumber : pinNumbers) {
            basicFrame.bowl(Pins.create(pinNumber));
        }
        // then
        assertAll(
                () -> assertThat(basicFrame.isEnd()).isEqualTo(end),
                () -> assertThat(basicFrame.symbol()).isEqualTo(symbol),
                () -> assertThat(basicFrame.score()).isEqualTo(score)
        );
    }
}
