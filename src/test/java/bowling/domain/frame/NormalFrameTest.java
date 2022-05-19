package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("9번 프레임에서 nextFrame 호출하면 FinalFrame 반환")
    @Test
    void nextFrame_WhenCalledAtMaxFrameNo_ReturnsFinalFrame() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.nextFrame(10)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest(name = "1~8번 프레임에서 nextFrame 호출하면 NormalFrame 반환")
    @ValueSource(ints = {1, 8})
    void nextFrame_WhenCalledNonMaxFrameNo_ReturnsNormalFrame(int frameNo) {
        NormalFrame frame = new NormalFrame(frameNo, 0);

        assertThat(frame.nextFrame(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void catGetScore_WhenPinsAreFull_ReturnsTrue() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.canGetScore()).isFalse();
        frame.addPin(7);
        assertThat(frame.canGetScore()).isTrue();
    }

    @Test
    void score_Miss() {
        NormalFrame frame = new NormalFrame(9, 5);
        frame.addPin(4);

        assertThat(frame.score()).isPresent();
        assertThat(frame.score()).contains(9);
    }

    @Test
    void score_Spare() {
        NormalFrame frame = new NormalFrame(9, 5);
        frame.addPin(5);

        assertThat(frame.score()).isEmpty();
        frame.nextFrame(5);
        assertThat(frame.score()).isPresent();
        assertThat(frame.score()).contains(15);
    }

    @Test
    void score_Strike() {
        NormalFrame frame = new NormalFrame(9, 10);

        assertThat(frame.score()).isEmpty();
        frame.nextFrame(5).addPin(5);
        assertThat(frame.score()).isPresent();
        assertThat(frame.score()).contains(20);
    }

    @Test
    void strikeBonusForPreviousFrame() {
        NormalFrame frame = new NormalFrame(9, 5);

        assertThat(frame.strikeBonusForPreviousFrame()).isEmpty();
        frame.addPin(5);
        assertThat(frame.strikeBonusForPreviousFrame()).contains(10);
    }

    @Test
    void strikeBonusForPreviousFrame_Strike() {
        NormalFrame frame = new NormalFrame(9, 10);

        assertThat(frame.strikeBonusForPreviousFrame()).isEmpty();
        frame.nextFrame(5);
        assertThat(frame.strikeBonusForPreviousFrame()).contains(15);
    }
}
