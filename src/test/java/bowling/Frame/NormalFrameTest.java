package bowling.Frame;

import bowling.bowl.*;
import bowling.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("프레임의 첫번째 투구에서 모든 핀을 쓰러트렸을 때 테스트")
    void strike_pitch(){
        NormalFrame frame = new NormalFrame(1);
        Frame next = frame.pitch(new Pins(10));

        assertThat(frame.getBowl()).isExactlyInstanceOf(Strike.class);
        assertThat(next.getIndex()).isEqualTo(2);
        assertThat(next.getBowl()).isExactlyInstanceOf(First.class);
    }

    @Test
    @DisplayName("프레임의 첫번째 투구에서 모든 핀을 쓰러트리지 못했을 때 테스트")
    void pitch(){
        NormalFrame frame = new NormalFrame(1);
        Frame next = frame.pitch(new Pins(8));

        assertThat(frame.getBowl()).isExactlyInstanceOf(Second.class);
        assertThat(next.getIndex()).isEqualTo(1);
        assertThat(next.getBowl()).isExactlyInstanceOf(Second.class);
    }

    @Test
    @DisplayName("프레임의 두번째 투구에서 모든 핀을 쓰려트렸을 때")
    void spare_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(8));
        Frame next = second.pitch(new Pins(2));

        assertThat(second.getBowl()).isExactlyInstanceOf(Spare.class);
        assertThat(next.getBowl()).isExactlyInstanceOf(First.class);
    }

    @Test
    @DisplayName("프레임의 두번째 투구에서도 모든 핀이 쓰려지지 않았을 때")
    void miss_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(8));
        Frame next = second.pitch(new Pins(1));

        assertThat(second.getBowl()).isExactlyInstanceOf(Miss.class);
        assertThat(next.getBowl()).isExactlyInstanceOf(First.class);
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못했을 때")
    void gutter_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(0));
        Frame next = second.pitch(new Pins(0));

        assertThat(second.getBowl()).isExactlyInstanceOf(Gutter.class);
        assertThat(next.getBowl()).isExactlyInstanceOf(First.class);
    }
}