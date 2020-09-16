package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 객체 테스트")
public class FrameTest {

    private static final Frame INIT_FRAME = new Frame(1);

    @DisplayName("생성")
    @Test
    public void create() {
        assertThat(INIT_FRAME).isEqualTo(new Frame(1));
    }

    @DisplayName("투구 테스트: 스트라이크 시 다음 프레임으로 이동")
    @Test
    public void bowl_strike() {
        Frame nextFrame = INIT_FRAME.bowl(10);
        assertThat(nextFrame).isEqualTo(new Frame(2));
    }

    @DisplayName("투구 테스트: 스페어 시 다음 프레임으로 이동")
    @Test
    public void bowl_spare() {
        Frame currentFrame = INIT_FRAME.bowl(5);
        Frame nextFrame = INIT_FRAME.bowl(5);
        assertThat(currentFrame).isEqualTo(new Frame(1));
        assertThat(nextFrame).isEqualTo(new Frame(2));
    }

    @DisplayName("toString 테스트")
    @Test
    public void toStringTest() {
        System.out.println(INIT_FRAME.toString());
        Frame frame_2 = INIT_FRAME.bowl(10);
        System.out.println(INIT_FRAME);
        frame_2.bowl(5);
        System.out.println(frame_2);
        Frame frame_3 = frame_2.bowl(5);
        System.out.println(frame_2);
        frame_3.bowl(8);
        System.out.println(frame_3);
        Frame frame_4 = frame_3.bowl(1);
        System.out.println(frame_3);
        frame_4.bowl(0);
        System.out.println(frame_4);
        frame_4.bowl(0);
        System.out.println(frame_4);
    }

}
