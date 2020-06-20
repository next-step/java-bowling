package bowling.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(1);
        finalFrame.play(2);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.MISS));
        bowlStates.add(new FrameBowlState(2, ScoreType.MISS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(bowlStates);
        //assertThat(finalFrame.()).isEqualTo(frameResult);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare(){
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(1);
        finalFrame.play(9);
        finalFrame.play(9);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.MISS));
        bowlStates.add(new FrameBowlState(9, ScoreType.SPARE));
        bowlStates.add(new FrameBowlState(9, ScoreType.MISS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(bowlStates);
        //assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike(){
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.play(10);
        finalFrame.play(9);
        finalFrame.play(9);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(10, ScoreType.STRIKE));
        bowlStates.add(new FrameBowlState(9, ScoreType.MISS));
        bowlStates.add(new FrameBowlState(9, ScoreType.MISS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(bowlStates);
        //assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

}
