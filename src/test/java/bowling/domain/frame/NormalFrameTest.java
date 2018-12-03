package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.record.Spare;
import bowling.domain.record.Strike;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class NormalFrameTest {

    @Test
    public void 프레임의_첫번째_투구후_같은_프레임을_반환하는지() {
        Frame frame = new NormalFrame(1);
        Frame afterFirstPitch = frame.rollBowlingBall(Pin.getInstance(0));

        assertThat(afterFirstPitch).isEqualTo(frame);
    }

    @Test
    public void 프레임의_첫번째_투구가_스트라크인경우_다음_프레임을_반환하는지() {
        Frame frame = new NormalFrame(1);
        Frame afterFirstPitch = frame.rollBowlingBall(Pin.getInstance(10));

        assertThat(afterFirstPitch).isNotEqualTo(frame);
    }

    @Test
    public void 프레임의_두번째_투구후_다음_프레임을_반환하는지() {
        Frame frame = new NormalFrame(1);
        frame.rollBowlingBall(Pin.getInstance(0));

        Frame afterSecondPitch = frame.rollBowlingBall(Pin.getInstance(1));

        assertThat(frame).isNotEqualTo(afterSecondPitch);
    }

    @Test
    public void 프레임의_첫번째_투구_결과() {
        Frame frame = new NormalFrame(1);
        assertThat(frame.recordFrameResult(Pin.getInstance(10))).isEqualTo(Strike.getInstance());
    }

    @Test
    public void 프레임의_결과가_스페어인_경우() {
        Frame frame = new NormalFrame(1);
        frame.recordFrameResult(Pin.getInstance(9));
        Record result = frame.recordFrameResult(Pin.getInstance(1));

        assertThat(result).isEqualTo(Spare.getInstance());
    }
}