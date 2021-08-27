package bowling.step2;

import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.outputView.PitchResult;
import bowling.step2.domain.TryNo;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchResultTest {

    @Test
    public void 결과_생성() {
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(TryNo.FIRST, 10);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(normalFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.STRIKE);
    }

    @Test
    public void 결과_생성2() {
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(TryNo.FIRST, 3);
        normalFrame.pitch(TryNo.SECOND, 7);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(normalFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.THREE);
        assertThat(pitchResults.get(1)).isEqualTo(PitchResult.SPARE);
    }

    @Test
    public void 마지막_프레임_결과_생성() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.pitch(TryNo.FIRST, 10);
        lastFrame.pitch(TryNo.SECOND, 10);
        lastFrame.pitch(TryNo.ADDITIONAL, 10);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(lastFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.STRIKE);
        assertThat(pitchResults.get(1)).isEqualTo(PitchResult.STRIKE);
        assertThat(pitchResults.get(2)).isEqualTo(PitchResult.STRIKE);
    }

    @Test
    public void 마지막_프레임_결과_생성2() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.pitch(TryNo.FIRST, 3);
        lastFrame.pitch(TryNo.SECOND, 7);
        lastFrame.pitch(TryNo.ADDITIONAL, 3);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(lastFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.THREE);
        assertThat(pitchResults.get(1)).isEqualTo(PitchResult.SPARE);
        assertThat(pitchResults.get(2)).isEqualTo(PitchResult.THREE);
    }

    @Test
    public void 마지막_프레임_결과_생성3() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.pitch(TryNo.FIRST, 10);
        lastFrame.pitch(TryNo.SECOND, 7);
        lastFrame.pitch(TryNo.ADDITIONAL, 3);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(lastFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.STRIKE);
        assertThat(pitchResults.get(1)).isEqualTo(PitchResult.SEVEN);
        assertThat(pitchResults.get(2)).isEqualTo(PitchResult.THREE);
    }

    @Test
    public void 마지막_프레임_결과_생성4() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.pitch(TryNo.FIRST, 10);
        lastFrame.pitch(TryNo.SECOND, 0);
        lastFrame.pitch(TryNo.ADDITIONAL, 3);
        //given, when
        List<PitchResult> pitchResults = PitchResult.findResultOf(lastFrame);

        assertThat(pitchResults.get(0)).isEqualTo(PitchResult.STRIKE);
        assertThat(pitchResults.get(1)).isEqualTo(PitchResult.GUTTER);
        assertThat(pitchResults.get(2)).isEqualTo(PitchResult.THREE);
    }
}
