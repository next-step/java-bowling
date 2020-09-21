package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    void creatTest(){
        LastFrame lastFrame = LastFrame.of();
        lastFrame.play(0);

        assertThat(lastFrame.isTerminate()).isEqualTo(false);
    }

    @Test
    void creatTest_allMiss(){
        LastFrame lastFrame = LastFrame.of();
        lastFrame.play(0);
        lastFrame.play(0);

        assertThat(lastFrame.isTerminate()).isEqualTo(true);
    }

    @Test
    void creatTest_allStrike(){
        LastFrame lastFrame = LastFrame.of();
        lastFrame.play(10);
        lastFrame.play(10);

        assertThat(lastFrame.isTerminate()).isEqualTo(false);
    }

    @Test
    void creatTest_spare(){
        LastFrame lastFrame = LastFrame.of();
        lastFrame.play(1);
        lastFrame.play(9);

        assertThat(lastFrame.isTerminate()).isEqualTo(false);
    }
}