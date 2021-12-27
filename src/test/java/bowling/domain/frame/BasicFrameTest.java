package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;
import bowling.domain.PinsTest;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
