package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 frame을 생성할 수 있다.")
    void createFirstFrameTest() {

        // given
        Frame excepted = NormalFrame.from(1, null, new Ready());

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isEqualTo(excepted);
    }

}