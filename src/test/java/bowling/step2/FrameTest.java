package bowling.step2;

import bowling.step2.domain.Frame;
import org.junit.jupiter.api.Test;

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
}
