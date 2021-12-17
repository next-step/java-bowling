package bowling.domain.state;

import bowling.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {

    @Test
    void 시작_상태를_생성한다() {
        //given
        State state = new Start();
        //when
        //then
        assertThat(state).isNotNull();
    }

    @Test
    void 일반_프레임_첫_번째_투구가_Strike_이면_상태는_End_이다() {
        //given
        Frame frame = NormalFrame.first();
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        frame.state().pitch(Pins.create(0), Pins.create(10), frame);
        //then
        assertThat(frame.state()).isInstanceOf(End.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,0", "9,1", "1,9"})
    void 일반_프레임_첫_번째_투구가_Miss_혹은_Gutter_이면_프레임_상태는_Progress_retry_false_이다(int existPinsCount, int fallDownPinsCount) {
        //given
        Frame frame = NormalFrame.first();
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        assertThat(existPinsCount + fallDownPinsCount).isEqualTo(10);
        frame.state().pitch(Pins.create(existPinsCount), Pins.create(fallDownPinsCount), frame);
        //then
        assertThat(frame.state()).isInstanceOf(Progress.class);
        assertThat(frame.state()).isEqualTo(new Progress(false));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,0,10,0", "9,1,1,8", "1,9,1,1", "1,9,0,1"})
    void 일반_프레임_첫_번째_투구가_Miss_Gutter_Spare_이면_두_번째_투구_후_프레임_상태는_End_이다(
            int existPinsCount, int fallDownPinsCount, int secondExistPinsCount, int secondFallDownPinsCount) {
        assertThat(existPinsCount + fallDownPinsCount).isEqualTo(10);
        //given
        Frame frame = NormalFrame.first();
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        //first pitch
        frame.state().pitch(Pins.create(existPinsCount), Pins.create(fallDownPinsCount), frame);
        assertThat(frame.state()).isInstanceOf(Progress.class);
        assertThat(frame.state()).isEqualTo(new Progress(false));

        //second pitch
        frame.state().pitch(Pins.create(secondExistPinsCount), Pins.create(secondFallDownPinsCount), frame);
        //then
        assertThat(frame.state()).isInstanceOf(End.class);
    }

    @Test
    void 마지막_프레임_첫_번째_투구가_미스_혹은_거터면_프레임_상태는_Progress_retry_false_이다() {
        //given
        Frame frame = FinalFrame.create(FrameInfo.create(9));
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        frame.state().pitch(Pins.create(1), Pins.create(9), frame);
        //then
        assertThat(frame.state()).isInstanceOf(Progress.class);
        assertThat(frame.state()).isEqualTo(new Progress(false));
    }

    @Test
    void 마지막_프레임_첫_번째_투구가_스트라이크면_프레임_상태는_Progress_retry_true_이다() {
        //given
        Frame frame = FinalFrame.create(FrameInfo.create(9));
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        frame.state().pitch(Pins.create(0), Pins.create(10), frame);
        //then
        assertThat(frame.state()).isInstanceOf(Progress.class);
        assertThat(frame.state()).isEqualTo(new Progress(true));
    }

    @Test
    void 마지막_프레임_첫_번째_투구가_스트라이크면_두_번째_투구_이후_프레임_상태는_Progress_retry_false_이다() {
        //given
        Frame frame = FinalFrame.create(FrameInfo.create(9));
        assertThat(frame.state()).isInstanceOf(Start.class);
        //when
        //first pitch
        frame.info().addPitch(Pitch.first());
        frame.state().pitch(Pins.create(0), Pins.create(10), frame);
        assertThat(frame.state()).isEqualTo(new Progress(true));

        //second pitch
        frame.info().addPitch(Pitch.first());
        frame.state().pitch(Pins.create(1), Pins.create(1), frame);
        //then
        assertThat(frame.state()).isInstanceOf(Progress.class);
        assertThat(frame.state()).isEqualTo(new Progress(false));
    }

    @Test
    void 종료_상태에서_투구하면_IllegalArgumentException_이_발생한다() {
        //given
        Frame frame = FinalFrame.create(FrameInfo.create(9));
        frame.changeState(new End());
        //when & then
        assertThatThrownBy(() -> frame.state().pitch(null, null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}