package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void 핀이_10개_이상_넘어가면_안된() {
        Assertions.assertThatThrownBy(() -> {
            new Frame(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("핀은 10개 이상 넘어갈 수 없습니다");
    }

}