package bowling.domain.frame;


import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    @Test
    void miss(){
        FinalFrame finalFrame = FinalFrame.newInstance();
        finalFrame.play(1);
        finalFrame.play(2);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(2, ScoreType.NUMS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
        //assertThat(finalFrame.()).isEqualTo(frameResult);
    }

    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    @Test
    void spare(){
        FinalFrame finalFrame = FinalFrame.newInstance();
        finalFrame.play(1);
        finalFrame.play(9);
        finalFrame.play(9);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(1, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(9, ScoreType.SPARE));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
        //assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    @Test
    void strike(){
        FinalFrame finalFrame = FinalFrame.newInstance();

        finalFrame.play(10);
        finalFrame.play(9);
        finalFrame.play(9);

        List<FrameBowlState> bowlStates = new ArrayList<>();
        bowlStates.add(new FrameBowlState(10, ScoreType.STRIKE));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));
        bowlStates.add(new FrameBowlState(9, ScoreType.NUMS));

        assertThat(finalFrame.getBowlStates()).isEqualTo(new FrameBowlStates(bowlStates));
        //assertThat(finalFrame.getResult()).isEqualTo(frameResult);
    }

}
