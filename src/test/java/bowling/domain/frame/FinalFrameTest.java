package bowling.domain.frame;

import bowling.domain.HitNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static bowling.domain.PinTest.*;
import static bowling.domain.rollresult.RollResultsTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    public static final FinalFrame FRAME = FinalFrame.of();
    public static final FinalFrame STRIKE_FRAME = FinalFrame.of(STRIKE_PIN, ONE_STRIKE_RESULTS);
    public static final FinalFrame DOUBLE_STRIKE_FRAME = FinalFrame.of(SPARE_PIN, DOUBLE_STRIKE_RESULTS);
    public static final FinalFrame SPARE_FRAME = FinalFrame.of(SPARE_PIN, ONE_SPARE_RESULTS);
    public static final FinalFrame MISS_FRAME = FinalFrame.of(MISS_PIN, MISS_RESULTS);
    public static final FinalFrame GUTTER_FRAME = FinalFrame.of(GUTTER_PIN, GUTTER_RESULTS);
    public static final FinalFrame ONE_ROLL_FRAME = FinalFrame.of(THREE_HIT_PIN, THREE_HIT_RESULTS);
    public static final FinalFrame SPARE_NEXT_FRAME = FinalFrame.of(THIRD_THREE_HIT_PIN, SPARE_THIRD_THREE_HIT);
    public static final FinalFrame STRIKE_NEXT_FRAME = FinalFrame.of(THIRD_THREE_HIT_PIN, STRIKE_THIRD_THREE_HIT);
    public FinalFrame frame;

    @BeforeEach
    void setUp() {
        frame = FinalFrame.of();
    }

    @Test
    void 프레임생성() {
        assertThat(FRAME).isEqualTo(FinalFrame.of());
    }

    @Test
    void 다음프레임생성() {
        assertThat(FRAME.next(5)).isEqualTo(FRAME);
        assertThat(FRAME.next(10)).isEqualTo(FRAME);
    }

    @Test
    void 더블스트라이크() {
        Frame resultFrame = frame.roll(HitNumber.of(10)).roll(HitNumber.of(10));
        assertThat(resultFrame).isEqualTo(DOUBLE_STRIKE_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        assertThat(resultFrame.totalScore().compareTo(20)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크아닌한번친프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(ONE_ROLL_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        assertThat(resultFrame.totalScore().compareTo(3)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 스페어프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(7));
        assertThat(resultFrame).isEqualTo(SPARE_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        assertThat(resultFrame.totalScore().compareTo(10)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 거터프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(GUTTER_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        assertThat(resultFrame.totalScore().compareTo(0)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 미스프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0)).roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(MISS_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        assertThat(resultFrame.totalScore().compareTo(0)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크후_재투구() {
        Frame resultFrame = frame.roll(HitNumber.of(10)).roll(HitNumber.of(10)).roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(STRIKE_NEXT_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        assertThat(resultFrame.totalScore().compareTo(23)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 스페어후_재투구() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(7)).roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(SPARE_NEXT_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        assertThat(resultFrame.totalScore().compareTo(13)).isEqualTo(0);
        System.out.println(resultFrame);
    }

    @Test
    void 잘못된_세번째투구() {
        assertThatThrownBy(() -> {
            frame.roll(HitNumber.of(3)).roll(HitNumber.of(4)).roll(HitNumber.of(3));
        }).isInstanceOf(IllegalStateException.class);
    }
}
