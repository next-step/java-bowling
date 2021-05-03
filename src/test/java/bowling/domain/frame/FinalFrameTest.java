package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.rollresult.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    public static final FinalFrame FRAME = FinalFrame.of();
    public static final FinalFrame STRIKE_FRAME = FinalFrame.of(Pin.of(1, 0), RollResult.of(Strike.of()));
    public static final FinalFrame SPARE_FRAME = FinalFrame.of(Pin.of(2, 0), RollResult.of(Spare.of(3, 7)));
    public static final FinalFrame MISS_FRAME = FinalFrame.of(Pin.of(2, 10), RollResult.of(Miss.of()));
    public static final FinalFrame GUTTER_FRAME = FinalFrame.of(Pin.of(2, 3), RollResult.of(Gutter.of(3, 4)));
    public static final FinalFrame ONE_ROLL_FRAME = FinalFrame.of(Pin.of(1, 7), RollResult.of(OneRollResultType.of(3)));
    public static final FinalFrame SPARE_NEXT_FRAME = FinalFrame.of(Pin.of(3, 7), RollResult.of(Spare.of(3, 10)));
    public static final FinalFrame STRIKE_NEXT_FRAME = FinalFrame.of(Pin.of(3, 7), RollResult.of(Strike.of(23)));
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
    void 스트라이크프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(10));
        assertThat(resultFrame).isEqualTo(STRIKE_FRAME);
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
    void 미스프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0)).roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(MISS_FRAME);
        assertThat(resultFrame.isFinished()).isTrue();
        System.out.println(resultFrame);
    }

    @Test
    void 거터프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(4));
        assertThat(resultFrame).isEqualTo(GUTTER_FRAME);
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
