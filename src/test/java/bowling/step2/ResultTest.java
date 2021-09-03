package bowling.step2;

import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.outputView.pitchResult.FrameResult;
import bowling.step2.outputView.pitchResult.PitchResult;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResultTest {
    @Test
    public void test() {
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(3);
        normalFrame.pitch(6);

        FrameResult frameResult = FrameResult.of(normalFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test0() {
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(3);
        normalFrame.pitch(7);

        FrameResult frameResult = FrameResult.of(normalFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test2() {
        NormalFrame normalFrame = NormalFrame.of(1);
        normalFrame.pitch(10);

        FrameResult frameResult = FrameResult.of(normalFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test3() {
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(3);
        lastFrame.pitch(7);
        lastFrame.pitch(7);

        FrameResult frameResult = FrameResult.of(lastFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test4() {
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(3);
        lastFrame.pitch(7);
        lastFrame.pitch(10);

        FrameResult frameResult = FrameResult.of(lastFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test5() {
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(10);
        lastFrame.pitch(10);
        lastFrame.pitch(3);

        FrameResult frameResult = FrameResult.of(lastFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }

    @Test
    public void test6() {
        LastFrame lastFrame = LastFrame.of(1);
        lastFrame.pitch(10);
        lastFrame.pitch(3);
        lastFrame.pitch(7);

        FrameResult frameResult = FrameResult.of(lastFrame);

        List<PitchResult> pitchResults = frameResult.getPitchResults();

        for (PitchResult pitchResult : pitchResults) {
            String result = pitchResult.result();
            System.out.println(result);
        }
    }
}
