package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    void creatTest(){
        FinalFrame finalFrame = FinalFrame.of();
        finalFrame.play(0);

        assertThat(finalFrame.isTerminate()).isEqualTo(false);
    }

    @Test
    void creatTest_allMiss(){
        FinalFrame finalFrame = FinalFrame.of();
        finalFrame.play(0);
        finalFrame.play(0);

        assertThat(finalFrame.isTerminate()).isEqualTo(true);
    }

    @Test
    void creatTest_allStrike(){
        FinalFrame finalFrame = FinalFrame.of();
        finalFrame.play(10);
        finalFrame.play(10);

        assertThat(finalFrame.isTerminate()).isEqualTo(false);
    }

    @Test
    void creatTest_spare(){
        FinalFrame finalFrame = FinalFrame.of();
        finalFrame.play(1);
        finalFrame.play(9);

        assertThat(finalFrame.isTerminate()).isEqualTo(false);
    }
}