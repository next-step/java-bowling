package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static bowling.domain.PinTest.*;
import static bowling.domain.rollresult.GutterTest.GUTTER;
import static bowling.domain.rollresult.MissTest.MISS;
import static bowling.domain.rollresult.OneHitTest.ONE_HIT_3;
import static bowling.domain.rollresult.SpareTest.SPARE_3;
import static bowling.domain.rollresult.StrikeTest.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private static final int MAX_INDEX = 10;
    public static final NormalFrame FRAME = NormalFrame.of();
    public static final NormalFrame STRIKE_FRAME = NormalFrame.of(STRIKE_PIN, STRIKE);
    public static final NormalFrame SPARE_FRAME = NormalFrame.of(SPARE_PIN, SPARE_3);
    public static final NormalFrame MISS_FRAME = NormalFrame.of(MISS_PIN, MISS);
    public static final NormalFrame GUTTER_FRAME = NormalFrame.of(GUTTER_PIN, GUTTER);
    public static final NormalFrame ONE_ROLL_FRAME = NormalFrame.of(THREE_HIT_PIN, ONE_HIT_3);
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
        Score score = STRIKE_FRAME.addScore(3).totalScore();
        assertThat(score.compareTo(13)).isEqualTo(0);
    }

    @Test
    void 스페어의추가점수() {
        Score score = SPARE_FRAME.addScore(5).totalScore();
        assertThat(score.compareTo(15)).isEqualTo(0);
    }

    @Test
    void 미스의추가점수는그대로(){
        Score score = MISS_FRAME.addScore(5).totalScore();
        assertThat(score.compareTo(0)).isEqualTo(0);
    }
}
