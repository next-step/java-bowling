package bowling;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss(){
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.play(1)).isEqualTo("1");
        assertThat(finalFrame.hasNextRound()).isEqualTo(true);
        assertThat(finalFrame.play(2)).isEqualTo("2");
        assertThat(finalFrame.hasNextRound()).isEqualTo(false);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare(){
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.play(1)).isEqualTo("1");
        assertThat(finalFrame.hasNextRound()).isEqualTo(true);
        assertThat(finalFrame.play(9)).isEqualTo("9");
        assertThat(finalFrame.hasNextRound()).isEqualTo(true);
        assertThat(finalFrame.play(9)).isEqualTo("9");
        assertThat(finalFrame.hasNextRound()).isEqualTo(false);
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike(){
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.play(10)).isEqualTo("X");
        assertThat(finalFrame.hasNextRound()).isEqualTo(true);
        assertThat(finalFrame.play(9)).isEqualTo("9");
        assertThat(finalFrame.hasNextRound()).isEqualTo(true);
        assertThat(finalFrame.play(9)).isEqualTo("9");
        assertThat(finalFrame.hasNextRound()).isEqualTo(false);
    }

}
