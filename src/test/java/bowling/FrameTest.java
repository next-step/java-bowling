package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    @DisplayName("처음 프레임을 만들면 1번 프레임부터 던진다")
    void test() {
        Frame frame = Frame.frame();
        assertThat(frame.orderToThrow()).isEqualTo(1);
    }

    @Test
    @DisplayName("10개를 쓰러트리면 2번째 프레임으로 넘어간다")
    void test2() {
        Frame frame = Frame.frame();
        frame.addChances(10);
        assertThat(frame.orderToThrow()).isEqualTo(2);
    }

    @Test
    @DisplayName("다 쓰러트리면 다음 순서는 -1이다")
    void test3() {
        Frame frame = Frame.frame();
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        frame.addChances(10);
        assertThat(frame.orderToThrow()).isEqualTo(-1);
    }

}
