package bowling.domain.frame;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BasicFrameTest {
    @DisplayName("플레이 후 프레임이 종료되지 않으면 같은 프레임을 반환")
    @Test
    void play_ReturnSameFrame_IfFrameIsNotFinished() {
        Frame frame = new BasicFrame(FrameNumber.of(FrameNumber.START_NUMBER));
        Frame next = frame.play(PinCount.of(5));
        assertThat(frame).isEqualTo(next);
    }

    @DisplayName("스트라이크 후 다음 프레임 반환")
    @Test
    void play_ReturnNextFrame_IfFrameIsFinishedByStrike() {
        FrameNumber frameNumber = FrameNumber.of(FrameNumber.START_NUMBER);
        Frame frame = new BasicFrame(frameNumber);
        Frame next = frame.play(PinCount.MAX_PINS);
        assertThat(next.getFrameNumber()).isEqualTo(frameNumber.next());
    }

    @DisplayName("두 번 투구 후 다음 프레임 반환")
    @Test
    void play_ReturnNextFrame_IfFrameIsFinishedBySecondPitched() {
        FrameNumber frameNumber = FrameNumber.of(FrameNumber.START_NUMBER);
        Frame frame = new BasicFrame(frameNumber);
        Frame next = frame.play(PinCount.of(5));
        next = next.play(PinCount.of(5));
        assertThat(next.getFrameNumber()).isEqualTo(frameNumber.next());
    }

    @DisplayName("한 번만 투구 했을 경우 canCalculate false 리턴")
    @Test
    void canCalculate_ReturnFalse_IfOncePitched() {
        Frame frame = new BasicFrame(FrameNumber.of(1));
        Score score = frame.play(PinCount.of(5))
                .calculateScore();
        assertThat(score.canCalculate()).isFalse();
    }

    @DisplayName("스트라이크 후 한 번만 투구 했을 경우 canCalculate false 리턴")
    @Test
    void canCalculate_ReturnFalse_IfStrikeAndOncePitched() {
        Frame frame = new BasicFrame(FrameNumber.of(1));
        Score score = frame.play(PinCount.MAX_PINS)
                .play(PinCount.MAX_PINS)
                .calculateScore();
        assertThat(score.canCalculate()).isFalse();
    }

    @DisplayName("미스일 때 점수 계산")
    @Test
    void calculateScore_ReturnScore_IfMiss() {
        Frame frame = new BasicFrame(FrameNumber.of(1));
        frame.play(PinCount.of(6)).play(PinCount.of(3));
        assertAll(
                () -> assertThat(frame.calculateScore()).isEqualTo(new Score(9, Left.ZERO)),
                () -> assertThat(frame.calculateScore().canCalculate()).isTrue()
        );
    }

    @DisplayName("스페어 후 투구 한 경우 점수 계산")
    @Test
    void calculateScore_ReturnScore_IfSpareAndOncePitched() {
        Frame frame = new BasicFrame(FrameNumber.of(1));
        Frame next = frame.play(PinCount.of(6)).play(PinCount.of(4));
        next.play(PinCount.of(8));
        assertAll(
                () -> assertThat(frame.calculateScore()).isEqualTo(new Score(18, Left.ZERO)),
                () -> assertThat(frame.calculateScore().canCalculate()).isTrue()
        );
    }

    @DisplayName("스트라이크 후 두 번 투구 한 경우 점수 계산")
    @Test
    void calculateScore_ReturnScore_IfStrikeAndTwicePitched() {
        Frame frame = new BasicFrame(FrameNumber.of(1));
        Frame next = frame.play(PinCount.MAX_PINS).play(PinCount.MAX_PINS);
        next.play(PinCount.MAX_PINS);
        assertAll(
                () -> assertThat(frame.calculateScore()).isEqualTo(new Score(30, Left.ZERO)),
                () -> assertThat(frame.calculateScore().canCalculate()).isTrue()
        );
    }

}
