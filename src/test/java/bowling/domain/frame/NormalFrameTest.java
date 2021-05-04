package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.rollresult.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private static final int MAX_INDEX = 10;
    public static final NormalFrame FRAME = NormalFrame.of();
    public static final NormalFrame STRIKE_FRAME = NormalFrame.of(Pin.of(1, 0), Strike.of());
    public static final NormalFrame SPARE_FRAME = NormalFrame.of(Pin.of(2, 0), Spare.of(3));
    public static final NormalFrame MISS_FRAME = NormalFrame.of(Pin.of(2, 10), Miss.of());
    public static final NormalFrame GUTTER_FRAME = NormalFrame.of(Pin.of(1, 10), Gutter.of());
    public static final NormalFrame ONE_ROLL_FRAME = NormalFrame.of(Pin.of(1, 7), OneHit.of(3));
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
        assertThat(FRAME.next(MAX_INDEX - 1)).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 스트라이크프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(10));
        assertThat(resultFrame).isEqualTo(STRIKE_FRAME);
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크아닌한번친프레임() {
        assertThat(frame.roll(HitNumber.of(3))).isEqualTo(ONE_ROLL_FRAME);
    }

    @Test
    void 거터프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(GUTTER_FRAME);
        System.out.println(resultFrame);
    }

    @Test
    void 스페어프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(3)).roll(HitNumber.of(7));
        assertThat(resultFrame).isEqualTo(SPARE_FRAME);
        System.out.println(resultFrame);
    }

    @Test
    void 미스프레임() {
        Frame resultFrame = frame.roll(HitNumber.of(0)).roll(HitNumber.of(0));
        assertThat(resultFrame).isEqualTo(MISS_FRAME);
        System.out.println(resultFrame);
    }

    @Test
    void 스트라이크의추가점수() {
//        assertThat(STRIKE_FRAME.addScore(3).);
    }

}
