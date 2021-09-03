package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("bowl test")
    @Test
    void bowl() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(Pins.of(4));

        assertThat(frame.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        NormalFrame frame = new NormalFrame(1);

        assertThat(frame.isFinish()).isFalse();

        frame.bowl(Pins.of(4));
        assertThat(frame.isFinish()).isFalse();

        frame.bowl(Pins.of(3));
        assertThat(frame.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish(){
        NormalFrame frame = new NormalFrame(1);

        frame.bowl(Pins.of(10));
        assertThat(frame.isFinish()).isTrue();

    }

    @DisplayName("마지막 10번째 Frame 은 frameNumber 10")
    @Test
    void NormalFrameLastToFinalFrameGetNumber(){
        NormalFrame frame = new NormalFrame(9);
        frame.bowl(Pins.of(10));

        Frame finalFrame = frame.next();

        assertThat(finalFrame.getFrameNumber()).isEqualTo(10);
    }

    @DisplayName("마지막 10번째 Frame 은 FinalFrame 이므로 pin 두번 Strike 면 종료가 false")
    @Test
    void isEndTest(){
        NormalFrame frame = new NormalFrame(9);
        frame.bowl(Pins.of(10));

        Frame finalFrame = frame.next();

        finalFrame.bowl(Pins.of(10));
        finalFrame.bowl(Pins.of(10));

        assertThat(finalFrame.isFinish()).isFalse();
    }


}
