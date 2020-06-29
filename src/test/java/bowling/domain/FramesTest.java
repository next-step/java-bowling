package bowling.domain;

import bowling.BowlingGame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    public void test_라운드는_1부터_시작() {
        Frames frames = new Frames(BowlingGame.FINAL_ROUND);
        assertThat(frames.getCurrentRound()).isEqualTo(1);
    }

    @Test
    public void test_프레임이_종료되면_라운드가_증가한다() {
        Frames frames = new Frames(BowlingGame.FINAL_ROUND);
        frames.bowling(10);
        assertThat(frames.getCurrentRound()).isEqualTo(2);
    }

    @Test
    public void test_프레임이_종료되지않으면_라운드가_유지된다() {
        Frames frames = new Frames(BowlingGame.FINAL_ROUND);
        frames.bowling(5);
        assertThat(frames.getCurrentRound()).isEqualTo(1);
    }
}
