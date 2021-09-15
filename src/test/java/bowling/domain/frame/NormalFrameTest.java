package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임을 생성할 수 있다.")
    void createFirstFrameTest() {

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(NormalFrame.class);
    }

}