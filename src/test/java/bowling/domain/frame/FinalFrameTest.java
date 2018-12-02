package bowling.domain.frame;

import bowling.domain.record.Record;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FinalFrameTest {

    @Test
    public void 마지막_프레임_첫투구가_스트라이크인_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(10);
        frame.rollBowlingBall(10);
        frame.rollBowlingBall(10);

        String result = frame.getRecords().toString();

        assertThat(result).isEqualTo("X|X|X");
    }

    @Test
    public void 마지막_프레임_스페어인_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(5);
        frame.rollBowlingBall(5);
        frame.rollBowlingBall(5);

        String result  = frame.getRecords().toString();

        assertThat(result).isEqualTo("5|/|5");
    }

    @Test
    public void 마지막_프레임_스페어나_스트라이크가_아닌_경우() {
        Frame frame = new FinalFrame(10);
        frame.rollBowlingBall(4);
        frame.rollBowlingBall(4);

        String result = frame.getRecords().toString();

        assertThat(result).isEqualTo("4|4");
    }

}
