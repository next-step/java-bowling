package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractFrameTest {

    @Test
    @DisplayName("현재 frame을 받아서 마지막 진행된 frame을 받아올 수 있다.")
    void lastFrameTest() {

        // given
        Frame thirdFrame = NormalFrame.from(3, null, null);
        Frame secondFrame = NormalFrame.from(2, thirdFrame, null);
        Frame firstFrame = NormalFrame.from(1, secondFrame, null);

        // when
        Frame result = firstFrame.lastFrame();

        // then
        assertThat(result).isEqualTo(thirdFrame);
    }

    @Test
    @DisplayName("마지막 frame이 있으면 마지막 frame을 반환하게 된다.")
    void lastFrameFinalReturnTest() {

        // given
        Frame thirdFrame = FinalFrame.createFinalFrame();
        Frame secondFrame = NormalFrame.from(9, thirdFrame, null);
        Frame firstFrame = NormalFrame.from(8, secondFrame, null);

        // when
        Frame result = firstFrame.lastFrame();

        // then
        assertThat(result).isEqualTo(thirdFrame);
    }

}