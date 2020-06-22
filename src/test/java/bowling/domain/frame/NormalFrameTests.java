package bowling.domain.frame;

import bowling.domain.frameStatus.NormalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTests {
    private static final int FIVE = 5;
    private static final int TEN = 10;

    @DisplayName("초구로 맞춘 핀의 수를 입력받아서 해당 프레임을 시작할 수 있다.")
    @Test
    void createTest() {
        NormalFrame normalFrame = NormalFrame.start(FIVE);

        assertThat(normalFrame).isEqualTo(new NormalFrame(1, NormalFrameStatus.bowlFirst(FIVE), null));
    }
}
