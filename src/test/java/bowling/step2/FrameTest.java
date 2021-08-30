package bowling.step2;

import bowling.step2.domain.Frame;
import bowling.step2.domain.FrameGroup;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    @Test
    public void pitchSuccess() {
        //given
        Frame frame = Frame.of(1);

        //when
        frame.pitch(5);
        frame.pitch(5);

        //then
    }

    @Test
    public void totalPitchCountNotInRange() {
        //given
        Frame frame = Frame.of(1);

        //when
        assertThatThrownBy(() -> {
            frame.pitch(5);
            frame.pitch(6);
        }).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void makeNextFrame() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        for (int i = 0; i < 10; i++) {
            frameGroup.nextFrame();
        }

        //then
    }

    @Test
    public void makeNextFrameFail() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        assertThatThrownBy(() -> {
            for (int i = 0; i < 11; i++) {
                frameGroup.nextFrame();
            }
        }).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void makeNextFrameWithPitches() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        frameGroup.pitch(5);
        frameGroup.pitch(5);
        frameGroup.pitch(5);
        frameGroup.pitch(5);

        //then
        assertThat(frameGroup.currentSize()).isEqualTo(3);
    }

    @Test
    public void makeNextFrameWithPitches2() {
        //given
        FrameGroup frameGroup = FrameGroup.of();

        //when
        frameGroup.pitch(5);
        frameGroup.pitch(5);
        frameGroup.pitch(5);
        frameGroup.pitch(5);
        frameGroup.pitch(5);

        //then
        assertThat(frameGroup.currentSize()).isEqualTo(3);
    }
}
