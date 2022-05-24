package bowling.Frame;

import bowling.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    @Test
    @DisplayName("마지막 프레임에서 스트라이크 한 번을 더 투구할 수 있다.")
    void 마지막_프레임_투구_한번더_스트라이크(){
        Frame finalFrame = new FinalFrame(10);
        finalFrame.pitch(new Pins(10));

        finalFrame.pitch(new Pins(4));
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크시 두번더 투구하면 예외가 생긴다.")
    void 마지막_프레임_투구_두번더_스트라이크(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(10));

        finalFrame.pitch(new Pins(1));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(2));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어 한 번을 더 투구할 수 있다.")
    void 마지막_프레임_투구_한번더_스페어(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(4));
        finalFrame.pitch(new Pins(6));

        finalFrame.pitch(new Pins(4));
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어시 두번더 투구하면 예외가 생긴다.")
    void 마지막_프레임_투구_두번더_스페어(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(4));
        finalFrame.pitch(new Pins(6));

        finalFrame.pitch(new Pins(1));
        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(2));
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("마지막 프레임에서 Miss일때 한 번더 투구하면 예외를 던진다.")
    void 마지막_프레임_투구_예외_miss(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(5));
        finalFrame.pitch(new Pins(0));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("마지막 프레임에서 gutter 일때 한 번더 투구하면 예외를 던진다.")
    void 마지막_프레임_투구_예외_gutter(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(0));
        finalFrame.pitch(new Pins(0));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}