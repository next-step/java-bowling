package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 투스트라이크_미스_점수() {
        Frames frames = new Frames(new NormalFrame(1));
        frames.add(frames.getFrame(1).bowl(10));
        frames.add(frames.getFrame(2).bowl(10));
        frames.add(frames.getFrame(3).bowl(5).bowl(4));
        assertThat(frames.calculateTotalScore(1).getTotalScores().get(0)).isEqualTo(25);
    }

    @Test
    void 스트라이크_미스_점수() {
        Frames frames = new Frames(new NormalFrame(1));
        frames.add(frames.getFrame(1).bowl(10));
        frames.add(frames.getFrame(2).bowl(5).bowl(4));
        assertThat(frames.calculateTotalScore(1).getTotalScores().get(0)).isEqualTo(19);
        assertThat(frames.calculateTotalScore(2).getTotalScores().get(1)).isEqualTo(28);
    }

    @Test
    void 쓰리스트라이크_점수_계산_될때() {
        Frames frames = new Frames(new NormalFrame(1));
        frames.add(frames.getFrame(1).bowl(10));
        frames.add(frames.getFrame(2).bowl(10));
        frames.add(frames.getFrame(3).bowl(10));
        assertThat(frames.calculateTotalScore(1).getTotalScores().get(0)).isEqualTo(30);
    }
}