package bowling.step2;

import bowling.step2.domain.NormalFrame;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    @Test
    public void 투구_성공() {
        //given
        NormalFrame normalFrame = new NormalFrame(1);

        //when
        normalFrame.pitch(1, 10);

        //then
        assertThat(normalFrame.sum()).isEqualTo(10);
    }

    @Test
    public void 투구_성공2() {
        //given
        NormalFrame normalFrame = new NormalFrame(1);

        //when
        normalFrame.pitch(1, 3);
        normalFrame.pitch(2, 7);

        //then
        assertThat(normalFrame.sum()).isEqualTo(10);
    }

    @Test
    public void 투구_실패() {
        //given
        NormalFrame normalFrame = new NormalFrame(1);

        //when, then
        assertThatThrownBy(() -> {
            normalFrame.pitch(1, 3);
            normalFrame.pitch(2, 9);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 투구_실패2() {
        //given
        NormalFrame normalFrame = new NormalFrame(1);

        //when, then
        assertThatThrownBy(() -> {
            normalFrame.pitch(1, 11);
        }).isInstanceOf(RuntimeException.class);
    }
}
