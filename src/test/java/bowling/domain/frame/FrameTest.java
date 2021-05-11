package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.Start;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FrameTest {

    @DisplayName("1~9프레임 첫 투구 테스트")
    @Test
    void pitchTest() {
        Frame firstFrame = new Frame(NormalRound.createOf(), null);
        Frame pitchFrame = firstFrame.pitch(5);
        Frame expectedFrame = new Frame(new NormalRound(1, new Ready(5)), firstFrame);

        assertEquals(pitchFrame, expectedFrame);
    }

    @DisplayName("1~9프레임 두번째 투구 테스트")
    @Test
    void secondPitchTest() {
        Frame firstFrame = new Frame(new NormalRound(1, new Ready(5)), null);
        Frame pitchFrame = firstFrame.pitch(5);

        Frame expectedBeforeframe = new Frame(new NormalRound(1, new Spare(5, 5)), firstFrame);
        Frame expectedFrame = new Frame(new NormalRound(2, Start.createOf()), expectedBeforeframe);


        assertEquals(pitchFrame.getPreviousFrame(), expectedFrame.getPreviousFrame());
    }
    
    @DisplayName("1~9프레임 다음라운드로 넘어가는지 테스트")
    @Test
    void nextRoundFrameTest() {
        Frame firstFrame = new Frame(new NormalRound(1, new Ready(5)), null);
        Frame pitchFrame = firstFrame.pitch(5);

        assertEquals(pitchFrame.getFrameRoundCount(), 2);
    }

    @DisplayName("9프레임에서 파이널 라운드로 넘어가는지 테스트")
    @Test
    void moveFinalRoundFrameTest() {
        Frame firstFrame = new Frame(new NormalRound(9, new Ready(5)), null);
        Frame pitchFrame = firstFrame.pitch(5);

        assertFalse(pitchFrame.isNormalRound());
    }

    @DisplayName("10프레임에서 pitch count 테스트")
    @Test
    void nextFinalRoundTest() {
        Frame frame = new Frame(new FinalRound(1, Start.createOf()), null);
        Frame pitchFrame = frame.pitch(5);

        Frame expectedFrame = new Frame(new FinalRound(2, new Ready(5)), frame);

        assertEquals(pitchFrame, expectedFrame);
    }

    @DisplayName("10프레임에서 두번째 투구시 종료 테스트")
    @Test
    void frameEndBySecondPitchTest() {
        Frame startFrame = new Frame(new FinalRound(1, Start.createOf()), null);
        Frame firstFrame = startFrame.pitch(5);
        Frame secondFrame = firstFrame.pitch(4);

        assertTrue(secondFrame.isEnd());
    }

    @DisplayName("10프레임에서 두번째 투구시 Spare일 경우 미종료 테스트")
    @Test
    void frameEndSpareTest() {
        Frame startFrame = new Frame(new FinalRound(1, Start.createOf()), null);
        Frame firstFrame = startFrame.pitch(5);
        Frame secondFrame = firstFrame.pitch(5);

        assertFalse(secondFrame.isEnd());
    }

    @DisplayName("10프레임에서 두번째 투구시 Strike일 경우 미종료 테스트")
    @Test
    void frameEndStrikeTest() {
        Frame startFrame = new Frame(new FinalRound(1, Start.createOf()), null);
        Frame firstFrame = startFrame.pitch(10);
        Frame secondFrame = firstFrame.pitch(5);

        assertFalse(secondFrame.isEnd());
    }

    @DisplayName("10프레임에서 세번째 투구시 종료 테스트")
    @Test
    void frameEndThirdPitchTest() {
        Frame startFrame = new Frame(new FinalRound(1, Start.createOf()), null);
        Frame firstFrame = startFrame.pitch(10);
        Frame secondFrame = firstFrame.pitch(5);
        Frame thridFrame = secondFrame.pitch(5);

        assertTrue(thridFrame.isEnd());
    }

}