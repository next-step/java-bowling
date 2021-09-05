package bowling.step2;

import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.domain.pitchresult.FrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    @DisplayName("한 프레임에서 두번의 pitch의 합이 총 10이 안될 경우")
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
    @DisplayName("한 프레임에서 두번의 pitch의 합이 총 10인 경우")
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
    @DisplayName("한 프레임에서 한번의 pitch가 10인 경우")
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
    @DisplayName("마지막 프레임에서 앞에 두번의 pitch 합이 10이고 마지막 pitch가 10미만인 경우")
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
    @DisplayName("마지막 프레임에서 앞에 두번의 pitch 합이 10이고 마지막 pitch가 10인 경우")
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
    @DisplayName("마지막 프레임에서 앞에 두번의 pitch가 각각 10이고 마지막 pitch가 10미만인 경우")
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
    @DisplayName("마지막 프레임에서 첫번째 pitch가 10이고 뒤에 두번의 pitch가 10인 경우")
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
