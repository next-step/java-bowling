package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FrameScoreTest {
    @Test
    void 스트라이크_미스_상황_일때() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(5).bowl(3).bowl(5).bowl(3);
        FrameScore frameScore = normalFrame.getFrameScore();
        assertThat(frameScore.getScore()).isEqualTo(18);
    }

    @Test
    void test() {
        NormalFrame normalFrame = new NormalFrame(1);
        Frame frame = normalFrame.bowl(10);
        Frame frame1 = frame.bowl(5);
        Frame frame2 = frame1.bowl(3);
        FrameScore frameScore = normalFrame.getFrameScore();
        assertThat(frameScore.getScore()).isEqualTo(18);
    }

    @Test
    void 스트라이크_상황_일때() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10);
        FrameScore frameScore = normalFrame.getFrameScore();
        assertThat(frameScore.getScore()).isEqualTo(-1);
    }
}