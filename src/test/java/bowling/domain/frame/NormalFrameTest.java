package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private static final int STRIKE = 10;
    private static final int GUTTER = 0;
    private static final int HALF = 5;

    private NormalFrame normalFrame;

    @BeforeEach
    void setup() {
        normalFrame = new NormalFrame();
    }

    @Test
    @DisplayName("스트라이크일 때 한번만에 프레임이 종료된다")
    void strikeEndsFrame() {
        normalFrame.bowl(STRIKE);
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스페어일 때는 두번만에 프레임이 종료된다")
    void spareEndsFrame() {
        normalFrame.bowl(GUTTER);
        normalFrame.bowl(STRIKE);
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("보너스점수가 없는 케이스에서 두번만에 프레임이 종료된다")
    void noBonusEndsFrame() {
        normalFrame.bowl(GUTTER);
        normalFrame.bowl(HALF);
        assertThat(normalFrame.isEnd()).isTrue();
    }

}
