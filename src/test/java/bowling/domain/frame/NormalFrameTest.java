package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.Start;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("프레임 결과 테스트")
    @Test
    void pitchTest() {
        NormalFrame normalFrame = new NormalFrame(Round.createOf(), null);

        Frame frame = new NormalFrame(new Round(1, new Ready(5)), normalFrame);
        Frame pitchFrame = normalFrame.pitch(5);
        
        assertEquals(frame, pitchFrame);
    }
    
    @DisplayName("프레임이 끝났을 경우 프레임 결과 피치 테스트")
    @Test
    void pitchEndAfter() {
        NormalFrame normalFrame = new NormalFrame(new Round(1, new Ready(5)), null);
        Frame pitchFrame = normalFrame.pitch(5);
        Frame responseFrame = pitchFrame.getFrameResponse().getFrame();
        Frame expectedFrame = new NormalFrame(new Round(1, new Spare(5, 5)), pitchFrame);

        assertEquals(responseFrame, expectedFrame);
    }
    
    @DisplayName("다음 라운드로 이동하는지 테스트")
    @Test
    void pitch() {
        NormalFrame normalFrame = new NormalFrame(new Round(1, new Ready(5)), null);
        Frame firstPitchFrame = normalFrame.pitch(10);
        Frame secondPitchFrame = firstPitchFrame.pitch(5);

        NormalFrame expectedFrame = new NormalFrame(new Round(2, new Ready(5)), firstPitchFrame);

        assertEquals(secondPitchFrame, expectedFrame);
    }

    @DisplayName("9프레임이 종료할경우 FinalFrame으로 가는지 테스트")
    @Test
    void finalFrameTest() {
        Frame normalFrame = new NormalFrame(new Round(9, new Ready(5)), null);
        Frame pitch = normalFrame.pitch(5);

        assertTrue(pitch instanceof FinalFrame);
    }

}