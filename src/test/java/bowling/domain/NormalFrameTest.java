package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 객체 테스트")
public class NormalFrameTest {

    private Frame frame;

    @BeforeEach
    public void setup() {
        frame = new NormalFrame(1);
    }

    @DisplayName("생성")
    @Test
    public void create() {
        assertThat(frame).isEqualTo(new NormalFrame(1));
    }

    @DisplayName("투구 테스트: 스트라이크 시 다음 프레임으로 이동")
    @Test
    public void bowl_strike() {
        Frame nextNormalFrame = frame.bowl(10);
        assertThat(nextNormalFrame).isEqualTo(new NormalFrame(2));
    }

    @DisplayName("투구 테스트: 스페어 시 다음 프레임으로 이동")
    @Test
    public void bowl_spare() {
        Frame currentNormalFrame = frame.bowl(5);
        Frame nextNormalFrame = frame.bowl(5);
        assertThat(currentNormalFrame).isEqualTo(new NormalFrame(1));
        assertThat(nextNormalFrame).isEqualTo(new NormalFrame(2));
    }

    @DisplayName("toString 테스트")
    @Test
    public void toStringTest() {
        System.out.println(frame.toString());
        Frame normalFrame_2 = frame.bowl(10);
        System.out.println(frame);
        normalFrame_2.bowl(5);
        System.out.println(normalFrame_2);
        Frame normalFrame_3 = normalFrame_2.bowl(5);
        System.out.println(normalFrame_2);
        normalFrame_3.bowl(8);
        System.out.println(normalFrame_3);
        Frame normalFrame_4 = normalFrame_3.bowl(1);
        System.out.println(normalFrame_3);
        normalFrame_4.bowl(0);
        System.out.println(normalFrame_4);
        normalFrame_4.bowl(0);
        System.out.println(normalFrame_4);
    }

}
