package bowling.frame;

import bowling.HitNumber;
import bowling.Pin;
import bowling.rollresult.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public static final NormalFrame FRAME = NormalFrame.of();
    public static final NormalFrame STRIKE_FRAME = NormalFrame.of(Pin.of(1, 0), RollResult.of(Strike.of()));
    public static final NormalFrame SPARE_FRAME = NormalFrame.of(Pin.of(2, 0), RollResult.of(Spare.of(3, 7)));
    public static final NormalFrame MISS_FRAME = NormalFrame.of(Pin.of(2, 10), RollResult.of(Miss.of()));
    public static final NormalFrame GUTTER_FRAME = NormalFrame.of(Pin.of(2, 3), RollResult.of(Gutter.of(3, 4)));
    public static final NormalFrame ONE_ROLL_FRAME = NormalFrame.of(Pin.of(1, 7), RollResult.of(OneRollResultType.of(3)));
    public NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = NormalFrame.of();
    }

    @Test
    void 프레임생성() {
        assertThat(FRAME).isEqualTo(NormalFrame.of());
    }

    @Test
    void 다음프레임확인() {
        assertThat(FRAME.next(5)).isInstanceOf(NormalFrame.class);
        assertThat(FRAME.next(10)).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 스트라이크프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(10));
        assertThat(resultFrame).isEqualTo(STRIKE_FRAME);
    }

    @Test
    void 스트라이크아닌한번친프레임() {
        assertThat(frame.roll(HitNumber.of(3))).isEqualTo(ONE_ROLL_FRAME);
    }

    @Test
    void 스페어프레임() {
        assertThat(frame.roll(HitNumber.of(3)).roll(HitNumber.of(7))).isEqualTo(SPARE_FRAME);
    }

    @Test
    void 미스프레임() {
        assertThat(frame.roll(HitNumber.of(0)).roll(HitNumber.of(0))).isEqualTo(MISS_FRAME);
    }

    @Test
    void 거터프레임() {
        assertThat(frame.roll(HitNumber.of(3)).roll(HitNumber.of(4))).isEqualTo(GUTTER_FRAME);
    }
}
