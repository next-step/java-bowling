package bowling.step2;

import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.outputView.pitchResult.FrameResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    public void normalFrameResult() {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(3);
        normalFrame.pitch(6);
        String expected = "3|6";

        //when
        FrameResult frameResult = FrameResult.of(normalFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void normalFrameResult1() {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(3);
        normalFrame.pitch(7);
        String expected = "3|/";

        //when
        FrameResult frameResult = FrameResult.of(normalFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void normalFrameResult2() {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(10);
        String expected = "X";

        //when
        FrameResult frameResult = FrameResult.of(normalFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void lastFrameResult() {
        //given
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(3);
        lastFrame.pitch(7);
        lastFrame.pitch(7);
        String expected = "3|/|7";

        //when
        FrameResult frameResult = FrameResult.of(lastFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void lastFrameResult2() {
        //given
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(3);
        lastFrame.pitch(7);
        lastFrame.pitch(10);
        String expected = "3|/|X";

        //when
        FrameResult frameResult = FrameResult.of(lastFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void lastFrameResult3() {
        //given
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(10);
        lastFrame.pitch(10);
        lastFrame.pitch(3);
        String expected = "X|X|3";

        //when
        FrameResult frameResult = FrameResult.of(lastFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }

    @Test
    public void lastFrameResult4() {
        //given
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(10);
        lastFrame.pitch(3);
        lastFrame.pitch(7);
        String expected = "X|3|/";

        //when
        FrameResult frameResult = FrameResult.of(lastFrame);

        //then
        String pitchResults = frameResult.getPitchResults();
        assertThat(pitchResults).isEqualTo(expected);
    }
}
