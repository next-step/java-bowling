package bowling.domain.state;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static bowling.domain.PinTest.*;
import static bowling.domain.state.GutterTest.GUTTER;
import static bowling.domain.state.MissTest.MISS;
import static bowling.domain.state.OneHitTest.ONE_HIT_3;
import static bowling.domain.state.SpareTest.SPARE_3;
import static bowling.domain.state.StrikeTest.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

public class RollResultsTest {
    public static final RollResults ONE_STRIKE_RESULTS = RollResults.of(STRIKE);
    public static final RollResults DOUBLE_STRIKE_RESULTS = RollResults.of(Arrays.asList(STRIKE, STRIKE));
    public static final RollResults TRIPLE_STRIKE_RESULTS = RollResults.of(Arrays.asList(STRIKE, STRIKE, STRIKE));
    public static final RollResults ONE_SPARE_RESULTS = RollResults.of(SPARE_3);
    public static final RollResults MISS_RESULTS = RollResults.of(MISS);
    public static final RollResults GUTTER_RESULTS = RollResults.of(GUTTER);
    public static final RollResults THREE_HIT_RESULTS = RollResults.of(ONE_HIT_3);
    public static final RollResults SPARE_THIRD_THREE_RESULTS = RollResults.of(Arrays.asList(SPARE_3, ONE_HIT_3));
    public static final RollResults STRIKE_THIRD_THREE_RESULTS = RollResults.of(Arrays.asList(STRIKE, STRIKE, ONE_HIT_3));

    @Test
    void 결과생성() {
        assertThat(ONE_SPARE_RESULTS).isEqualTo(RollResults.of(SPARE_3));
    }

    @Test
    void 한번친후_miss() {
        assertThat(THREE_HIT_RESULTS.next(THREE_HIT_PIN, HitNumber.of(3))).isEqualTo(RollResults.of(Miss.of(3, 3)));
    }

    @Test
    void 한번친후_spare() {
        assertThat(THREE_HIT_RESULTS.next(THREE_HIT_PIN, HitNumber.of(7))).isEqualTo(ONE_SPARE_RESULTS);
    }


    @Test
    void Strike후_doubleStrike() {
        RollResults doubleStrike = ONE_STRIKE_RESULTS.next(STRIKE_PIN.reload(), HitNumber.of(10));
        assertThat(doubleStrike).isEqualTo(DOUBLE_STRIKE_RESULTS);
        assertThat(doubleStrike.next(SPARE_PIN.reload(), HitNumber.of(10))).isEqualTo(TRIPLE_STRIKE_RESULTS);
    }

    @Test
    void Strike후_스페어() {
        RollResults nextOne = ONE_STRIKE_RESULTS.next(STRIKE_PIN.reload(), HitNumber.of(3));
        assertThat(nextOne).isEqualTo(RollResults.of(Arrays.asList(STRIKE, ONE_HIT_3)));
        RollResults nextSpare = nextOne.next(Pin.of(2, 7), HitNumber.of(7));
        assertThat(nextSpare).isEqualTo(Arrays.asList(STRIKE, SPARE_3));
    }

    @Test
    void Spare후_한번침() {
        assertThat(ONE_SPARE_RESULTS.next(SPARE_PIN.reload(), HitNumber.of(3))).isEqualTo(SPARE_THIRD_THREE_RESULTS);
    }

    @Test
    void 마무리하였는지확인() {
        assertThat(ONE_STRIKE_RESULTS.isCleared()).isTrue();
        assertThat(ONE_SPARE_RESULTS.isCleared()).isTrue();
        assertThat(MISS_RESULTS.isCleared()).isFalse();
        assertThat(GUTTER_RESULTS.isCleared()).isFalse();
    }
}
