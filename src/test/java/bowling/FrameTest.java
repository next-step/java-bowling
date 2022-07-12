package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void 투구수가_10개_이상이면_안된다() {
        Assertions.assertThatThrownBy(() -> {
            new Frame(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("핀은 10개 이상 넘어갈 수 없습니다");
    }

    @Test
    void 투구수_10개면_스트라이크다() {
        Frame frame = new Frame(10);
        Assertions.assertThat(frame.isStrike()).isTrue();
    }
}