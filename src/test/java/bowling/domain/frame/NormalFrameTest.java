package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.record.Spare;
import bowling.domain.record.Strike;
import bowling.domain.score.Score;
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

    @Test
    public void 프레임_스코어에_보너스가_없는경우(){
        Frame frame = new NormalFrame(1);
        frame.recordFrameResult(Pin.getInstance(4));
        frame.recordFrameResult(Pin.getInstance(4));

        assertThat(Score.scoreForFrame(frame.calculateScore())).isEqualTo(8);
    }

    @Test
    public void 프레임_스코어에_스페어처리_보너스_계산() {
        Frame frame = new NormalFrame(1);
        frame = frame.rollBowlingBall(Pin.getInstance(5));

        Frame next = frame.rollBowlingBall(Pin.getInstance(5));
        next.rollBowlingBall(Pin.getInstance(4));

        assertThat(Score.scoreForFrame(frame.calculateScore())).isEqualTo(14);
    }

    @Test
    public void 프레임_스코어에_스트라이크처리_보너스_계산() {
        Frame frame = new NormalFrame(1);
        Frame next = frame.rollBowlingBall(Pin.getInstance(10));

        next.rollBowlingBall(Pin.getInstance(5));
        next.rollBowlingBall(Pin.getInstance(4));

        assertThat(Score.scoreForFrame(frame.calculateScore())).isEqualTo(19);
    }

    @Test
    public void 스트라이크를_세번_친_경우_보너스(){
        Frame frame = new NormalFrame(1);
        Frame next = frame.rollBowlingBall(Pin.getInstance(10));
        Frame next2 = next.rollBowlingBall(Pin.getInstance(10));
        next2.rollBowlingBall(Pin.getInstance(10));

        assertThat(Score.scoreForFrame(frame.calculateScore())).isEqualTo(30);
    }
}