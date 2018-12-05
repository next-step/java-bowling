package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.score.Score;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FinalFrameTest {

    @Test
    public void 마지막_프레임_첫투구가_스트라이크인_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(Pin.getInstance(10));
        frame.rollBowlingBall(Pin.getInstance(10));
        frame.rollBowlingBall(Pin.getInstance(10));

        String result = frame.getRecords().toString();

        assertThat(result).isEqualTo("X|X|X");
    }

    @Test
    public void 마지막_프레임_스페어인_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(Pin.getInstance(5));
        frame.rollBowlingBall(Pin.getInstance(5));
        frame.rollBowlingBall(Pin.getInstance(5));

        String result  = frame.getRecords().toString();

        assertThat(result).isEqualTo("5|/|5");
    }

    @Test
    public void 마지막_프레임_스페어나_스트라이크가_아닌_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(Pin.getInstance(4));
        frame.rollBowlingBall(Pin.getInstance(4));

        String result = frame.getRecords().toString();

        assertThat(result).isEqualTo("4|4");
    }

    @Test
    public void 마지막_프레임의_점수만_합산되는지_두번_쳤을경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(Pin.getInstance(5));
        frame.rollBowlingBall(Pin.getInstance(4));

        Score score = frame.calculateScore();

        assertThat(score.getScore()).isEqualTo(9);
    }

    @Test
    public void 마지막_프레임의_점수만_합산되는지_세번_쳤을경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(Pin.getInstance(10));
        frame.rollBowlingBall(Pin.getInstance(10));
        frame.rollBowlingBall(Pin.getInstance(10));

        Score score = frame.calculateScore();

        assertThat(score.getScore()).isEqualTo(30);
    }
}
