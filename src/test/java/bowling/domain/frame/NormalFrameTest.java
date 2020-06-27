package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("다음 프레임이 마지막이 아니면 NormalFrame을 생성한다")
    @Test
    void createNext_normal() {
        NormalFrame frame = NormalFrame.createFirst();
        Frame nextFrame = frame.createNext(false);

        assertThat(nextFrame).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("다음 프레임이 마지막이면 FinalFrame을 생성한다")
    @Test
    void createNext_final() {
        NormalFrame frame = NormalFrame.createFirst();
        Frame nextFrame = frame.createNext(true);

        assertThat(nextFrame).isInstanceOf(FinalFrame.class);
    }
}
