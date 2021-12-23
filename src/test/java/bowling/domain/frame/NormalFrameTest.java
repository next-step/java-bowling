package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("한 프레임 끝나고 다음 프레임 반환 테스트")
    void nextCreateTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame returnFrame = normalFrame.bowl(10);

        assertThat(returnFrame.getFrameNo()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임은 파이널 프레임 반환 검증")
    void finalCreateTest() {
        NormalFrame normalFrame = new NormalFrame(9);
        Frame returnFrame = normalFrame.bowl(10);

        assertThat(returnFrame.isNormalFrame()).isFalse();
    }
}
