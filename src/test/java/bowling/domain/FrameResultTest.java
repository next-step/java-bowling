package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameResultTest {

    @DisplayName("프레임이 끝나면 전 점수와 합산한다")
    @Test
    void addTotalScore() {
        FrameResult frameResult = new FrameResult("2|3", 5);
        int totalScore = frameResult.addTotalScore(10);
        assertThat(totalScore).isEqualTo(15);
    }

    @DisplayName("프레임이 끝나지 않은 상태에서 전 점수와 합산해본다.")
    @Test
    void addTotalScoreWhenUnScore() {
        FrameResult frameResult = new FrameResult("8|", -1);
        int totalScore = frameResult.addTotalScore(10);
        assertThat(totalScore).isEqualTo(-1);
    }

    @DisplayName("프레임 결과가 미스일때")
    @Test
    void getFrameResultMiss() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1).bowl(2);
        FrameResult frameResult = normalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("1|2", 3));
    }

    @DisplayName("프레임 결과가 스페어일때")
    @Test
    void getFrameResultSpare() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8).bowl(2).bowl(7);
        FrameResult frameResult = normalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("8|/", 17));
    }

    @DisplayName("프레임 결과가 스페어이면서 점수 합산이 아직 끝나지 않았을때")
    @Test
    void getFrameResultSpareReadyState() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8).bowl(2);
        FrameResult frameResult = normalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("8|/"));
    }

    @DisplayName("프레임 결과가 스트라이크일때")
    @Test
    void getFrameResultStrike() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(10).bowl(5);
        FrameResult frameResult = normalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("X", 25));
    }

    @DisplayName("프레임 결과가 스트라이크이면서 점수 합산이 아직 끝나지 않았을때")
    @Test
    void getFrameResultStrikeReadyState() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(10);
        FrameResult frameResult = normalFrame.getFrameResult();
        assertThat(frameResult).isEqualTo(new FrameResult("X"));
    }
}
