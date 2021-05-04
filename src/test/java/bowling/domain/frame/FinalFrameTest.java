package bowling.domain.frame;

import bowling.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    /*
       normal 프레임에서 round는 실제로 round지만
       final 프레임에서는 count로 사용하고 있습니다.
     */
    @DisplayName("피치시 round가 이동하는지 테스트")
    @Test
    void pitchMoveRoundTest() {
        FinalFrame finalFrame = FinalFrame.createOf(null);

        Frame pitchFrame = finalFrame.pitch(5);
        FinalFrame expectedFrame = new FinalFrame(new Round(2, new Ready(5)), finalFrame);

        assertEquals(pitchFrame, expectedFrame);
    }

    @DisplayName("파이널 프레임이 종료하는지 테스트 - 2번 pitch (추가기회 없을 경우)")
    @Test
    void isEndTrueTest() {
        FinalFrame finalFrame = FinalFrame.createOf(null);
        Frame pitch = finalFrame.pitch(5).pitch(4);

        assertTrue(pitch.isEnd());
    }

    @DisplayName("파이널 프레임이 종료하는지 테스트 - 2번 pitch (추가기회 있을 경우)")
    @Test
    void isEndFalseWithExtraChanceTest() {
        FinalFrame finalFrame = FinalFrame.createOf(null);
        Frame pitch = finalFrame.pitch(5).pitch(10);

        assertTrue(!pitch.isEnd());
    }

    @DisplayName("파이널 프레임이 종료하는지 테스트 - 3번 pitch (추가기회 있을 경우)")
    @Test
    void isEndTrueWithExtraChanceTest() {
        FinalFrame finalFrame = FinalFrame.createOf(null);
        Frame pitch = finalFrame.pitch(5).pitch(10).pitch(10);

        assertTrue(pitch.isEnd());
    }



}