package bowling.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    public void addTotalScore() throws Exception {
        FrameResult result = new FrameResult("8 | 1", 9);
        assertThat(result.addTotalScore(10)).isEqualTo(19);
    }

    @Test
    public void addTotalScoreWhenUnScore() throws Exception {
        FrameResult result = new FrameResult("8 | ", NormalFrame.UN_SCORE_STATE);
        assertThat(result.addTotalScore(10)).isEqualTo(NormalFrame.UN_SCORE_STATE);
    }

    @Test
    public void getFrameResultWhenMiss() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8).bowl(1);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("8 | 1", 9));
    }

    @Test
    public void getFrameResultWhenNotReady() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("8 | ", NormalFrame.UN_SCORE_STATE));
    }

    @Test
    public void getFrameResultWhenSpare() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8).bowl(2).bowl(9);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("8 | /", 19));
    }

    @Test
    public void getFrameResultWhenNotSpareNotReady() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(8).bowl(2);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("8 | /", NormalFrame.UN_SCORE_STATE));
    }

    @Test
    public void getFrameResultWhenStrike() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(10).bowl(8);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("X", 28));
    }

    @Test
    public void getFrameResultWhenNotStrikeNotReady() throws Exception {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(10);
        FrameResult result = normalFrame.getFrameResult();
        assertThat(result).isEqualTo(new FrameResult("X", NormalFrame.UN_SCORE_STATE));
    }
}
