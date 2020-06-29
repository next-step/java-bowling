package bowling.domain;

import bowling.BowlingGame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    public void test_라운드는_1부터_시작() {
        Frames frames = new Frames(BowlingGame.FINAL_FRAME);
        assertThat(frames.getCurrentFrame()).isEqualTo(1);
    }

    @Test
    public void test_프레임이_종료되면_프레임이_증가한다() {
        Frames frames = new Frames(BowlingGame.FINAL_FRAME);
        frames.bowling(10);
        assertThat(frames.getCurrentFrame()).isEqualTo(2);
    }

    @Test
    public void test_프레임이_종료되지않으면_프레임이_유지된다() {
        Frames frames = new Frames(BowlingGame.FINAL_FRAME);
        frames.bowling(5);
        assertThat(frames.getCurrentFrame()).isEqualTo(1);
    }
}
