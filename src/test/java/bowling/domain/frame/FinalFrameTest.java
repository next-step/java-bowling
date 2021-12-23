package bowling.domain.frame;

import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.progress.FirstHit;
import bowling.domain.state.progress.Start;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @Test
    void 마지막_프레임_Miss_Spare_면_상태는_Start_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 5);
        frame.run((bound) -> 5);
        //then
        assertThat(frame.state()).isInstanceOf(Start.class);
    }

    @Test
    void 마지막_프레임_Miss_Spare_Miss_면_상태는_FirstHit_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 5);
        frame.run((bound) -> 5);
        frame.run((bound) -> 5);
        //then
        assertThat(frame.state()).isInstanceOf(FirstHit.class);
    }

    @Test
    void 마지막_프레임_Strike_Miss_Spare_면_상태는_Spare_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 10);
        frame.run((bound) -> 5);
        frame.run((bound) -> 5);
        //then
        assertThat(frame.state()).isInstanceOf(Spare.class);
    }

    @Test
    void 마지막_프레임_Strike_Miss_Miss_면_상태는_Miss_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 10);
        frame.run((bound) -> 3);
        frame.run((bound) -> 3);
        //then
        assertThat(frame.state()).isInstanceOf(Miss.class);
    }

    @Test
    void 마지막_프레임_Strike_Miss_Gutter_면_상태는_Miss_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 10);
        frame.run((bound) -> 3);
        frame.run((bound) -> 0);
        //then
        assertThat(frame.state()).isInstanceOf(Miss.class);
    }

    @Test
    void 마지막_프레임_Strike_Strike_Strike_면_상태는_Miss_이다() {
        //given
        Frame frame = FinalFrame.create();
        //when
        frame.run((bound) -> 10);
        frame.run((bound) -> 10);
        frame.run((bound) -> 10);
        //then
        assertThat(frame.state()).isInstanceOf(Strike.class);
    }
}
