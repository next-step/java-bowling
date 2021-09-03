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

    @DisplayName("종료되면 다음 Frame 을 가르킴")
    @Test
    void isFinishNext(){
        NormalFrame frame = new NormalFrame(1);

        frame.bowl(Pins.of(3));
        frame.isFinish();
        assertThat(frame.next()).isNull();

        frame.bowl(Pins.of(5));
        frame.isFinish();
        assertThat(frame.next()).isNotNull();

    }

    @DisplayName("마지막 9번째 NormalFrame 의 next 는 Null")
    @Test
    void isFinalFrameNextNull(){
        NormalFrame frame = new NormalFrame(8);
        frame.bowl(Pins.of(10));
        frame.isFinish();
        assertThat(frame.next()).isNull();
    }
}
