package bowling;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(1);
        finalFrame.play(2);

        FrameResult frameResult = new FrameResult(Arrays.asList(1,2));
        assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(1);
        finalFrame.play(9);
        finalFrame.play(9);

        FrameResult frameResult = new FrameResult(Arrays.asList(1,9,9));
        assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike(){
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.play(10);
        finalFrame.play(9);
        finalFrame.play(9);

        FrameResult frameResult = new FrameResult(Arrays.asList(10,9,9));
        assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

}
