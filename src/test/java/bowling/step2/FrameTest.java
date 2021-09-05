package bowling.step2;

import bowling.step2.domain.FrameGroup;
import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    @Test
    public void pitchSuccess() {
        //given
        NormalFrame frame = NormalFrame.of(1);

        //when
        frame.pitch(5);
        frame.pitch(5);

        //then
        assertThat(frame.current()).isEqualTo(Arrays.asList(5, 5));
    }

    @Test
    public void pitchSuccess2() {
        //given
        LastFrame frame = LastFrame.of(10);

        //when
        frame.pitch(5);
        frame.pitch(5);
        frame.pitch(6);

        //then
        assertThat(frame.current()).isEqualTo(Arrays.asList(5, 5, 6));
    }

    @Test
    public void pitchFail() {
        //given
        NormalFrame frame = NormalFrame.of(1);

        //when
        frame.pitch(5);
        assertThatThrownBy(() -> frame.pitch(6)).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void pitchFail2() {
        //given
        LastFrame frame = LastFrame.of(10);

        //when
        frame.pitch(0);
        frame.pitch(0);
        assertThatThrownBy(() -> frame.pitch(6)).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void pitchFail3() {
        //given
        LastFrame frame = LastFrame.of(10);

        //when
        frame.pitch(5);
        assertThatThrownBy(() -> frame.pitch(7)).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void totalPitchCountNotInRange() {
        //given
        NormalFrame frame = NormalFrame.of(1);

        //when
        frame.pitch(5);
        assertThatThrownBy(() -> frame.pitch(6)).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void makeNextFrame() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        for (int i = 0; i < 9; i++) {
            frameGroup.nextFrame();
        }

        //then
        assertThat(frameGroup.currentSize()).isEqualTo(10);
    }

    @Test
    public void makeNextFrameFail() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        for (int i = 0; i < 9; i++) {
            frameGroup.nextFrame();
        }
        assertThatThrownBy(frameGroup::nextFrame).isInstanceOf(RuntimeException.class);

        //then
    }
}
