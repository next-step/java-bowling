package bowling.domain.frame;

import bowling.domain.state.end.End;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.progress.FirstHit;
import bowling.domain.state.progress.Start;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    void 일반_프레임_Miss_혹은_Gutter_면_상태는_FirstHit_이다() {
        //given
        Frame frame = NormalFrame.first();
        //when
        frame.run((bound) -> 5);
        //then
        assertThat(frame.state()).isInstanceOf(FirstHit.class);
    }

    @Test
    void 일반_프레임_Strike_면_상태는_End_이다() {
        //given
        Frame frame = NormalFrame.first();
        //when
        frame.run((bound) -> 10);
        //then
        assertThat(frame.state()).isInstanceOf(End.class);
        assertThat(frame.state()).isInstanceOf(Strike.class);
    }

    @Test
    void 일반_프레임_Miss_Spare_면_상태는_End_이다() {
        //given
        Frame frame = NormalFrame.first();
        //when
        frame.run((bound) -> 5);
        frame.run((bound) -> 5);
        //then
        assertThat(frame.state()).isInstanceOf(End.class);
        assertThat(frame.state()).isInstanceOf(Spare.class);
    }
}
