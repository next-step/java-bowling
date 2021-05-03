package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.rollresult.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    public static final FinalFrame FRAME = FinalFrame.of();
    public static final FinalFrame STRIKE_FRAME = FinalFrame.of(Pin.of(1, 0), RollResults.of(Strike.of()));
    public static final FinalFrame DOUBLE_STRIKE_FRAME = FinalFrame.of(Pin.of(2, 0), RollResults.of(Arrays.asList(Strike.of(),Strike.of())));
    public static final FinalFrame SPARE_FRAME = FinalFrame.of(Pin.of(2, 0), RollResults.of(Spare.of(3)));
    public static final FinalFrame MISS_FRAME = FinalFrame.of(Pin.of(2, 10), RollResults.of(Miss.of()));
    public static final FinalFrame GUTTER_FRAME = FinalFrame.of(Pin.of(1, 10), RollResults.of(Gutter.of()));
    public static final FinalFrame ONE_ROLL_FRAME = FinalFrame.of(Pin.of(1, 7), RollResults.of(OneHit.of(3)));
    public static final FinalFrame SPARE_NEXT_FRAME = FinalFrame.of(Pin.of(3, 7), RollResults.of(Arrays.asList(Spare.of(3), OneHit.of(3))));
    public static final FinalFrame STRIKE_NEXT_FRAME = FinalFrame.of(Pin.of(3, 7), RollResults.of(Arrays.asList(Strike.of(), Strike.of(), OneHit.of(3))));
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
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크아닌한번친프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(ONE_ROLL_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        System.out.println(resultFrame);
    }

    @Test
    void 스페어프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(7));
        assertThat(resultFrame).isEqualTo(SPARE_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        System.out.println(resultFrame);
    }

    @Test
    void 거터프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(GUTTER_FRAME);
        assertThat(resultFrame.isFinished()).isFalse();
        System.out.println(resultFrame);
    }

    @Test
    void 미스프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0)).roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(MISS_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크후_재투구() {
        Frame resultFrame = frame.roll(HitNumber.of(10)).roll(HitNumber.of(10)).roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(STRIKE_NEXT_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        System.out.println(resultFrame);
    }

    @Test
    void 스페어후_재투구() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(7)).roll(HitNumber.of(3));
        assertThat(resultFrame).isEqualTo(SPARE_NEXT_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        System.out.println(resultFrame);
    }

    @Test
    void 잘못된_세번째투구() {
        assertThatThrownBy(() -> {
            frame.roll(HitNumber.of(3)).roll(HitNumber.of(4)).roll(HitNumber.of(3));
        }).isInstanceOf(IllegalStateException.class);
    }
}
