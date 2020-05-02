package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.score.FrameScore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FramesTest {

    @Test
    void getCurrentFrameNumber() {
        Frame frame1 = getMockFrame(1, 0);
        Frame frame2 = getMockFrame(2, 0);
        Frames frames = new Frames(frame1, frame2);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);
    }

    private Frame getMockFrame(int frameNumber, int shotCount) {
        Frame frame = mock(Frame.class);
        when(frame.getFrameNumber())
                .thenReturn(frameNumber);
        when(frame.getShotsCount())
                .thenReturn(shotCount);
        return frame;
    }

    @Test
    void shot() {
        Frame frame1 = getMockFrame(true);
        Frame frame2 = getMockFrame(false);

        FrameScore frameScore1 = mock(FrameScore.class);
        when(frameScore1.isCalculated())
                .thenReturn(false);
        when(frame1.getFrameScore())
                .thenReturn(frameScore1);

        Frames frames = new Frames(frame1, frame2);
        frames.shot(5);

        verify(frameScore1)
                .addBonus(5);
        verify(frame2)
                .shot(5);
    }

    private Frame getMockFrame(boolean isFrameSet) {
        Frame frame1 = mock(Frame.class);
        when(frame1.isFrameSet())
                .thenReturn(isFrameSet);
        return frame1;
    }

    @Test
    void isGameSet() {
        Frame finalFrame = mock(Frame.class);
        when(finalFrame.getFrameNumber())
                .thenReturn(FinalFrame.FRAME_NUMBER);
        Frames frames = new Frames(finalFrame);
        assertThat(frames.isGameSet())
                .isFalse();

        when(finalFrame.isFrameSet())
                .thenReturn(true);

        assertThat(frames.isGameSet())
                .isTrue();
    }

    @Test
    void getCurrentFrameShotCount() {
        Frame frame1 = getMockFrame(1, 1);
        Frame frame2 = getMockFrame(2, 2);

        Frames frames = new Frames(frame1, frame2);

        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(2);
    }

    @Test
    void getScores() {
        Frame frame1 = getMockFrame(true);
        Frame frame2 = getMockFrame(true);
        Frame frame3 = getMockFrame(false);

        settingFrameScore(frame1, true, 11);
        settingFrameScore(frame2, false, 12);
        settingFrameScore(frame3, false, 13);

        Frames frames = new Frames(frame1, frame2, frame3);
        assertThat(frames.getScores())
                .hasSize(1)
                .containsExactly(11);
    }

    private void settingFrameScore(Frame mockFrame, boolean isCalculated, int getScore) {
        FrameScore mockFrameScore = mock(FrameScore.class);
        when(mockFrameScore.isCalculated())
                .thenReturn(isCalculated);
        when(mockFrameScore.getScore())
                .thenReturn(getScore);
        when(mockFrame.getFrameScore())
                .thenReturn(mockFrameScore);
    }
}
