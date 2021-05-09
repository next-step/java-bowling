package bowling.domain.rollresult;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static bowling.domain.rollresult.GutterTest.GUTTER;
import static bowling.domain.rollresult.MissTest.MISS;
import static bowling.domain.rollresult.OneHitTest.ONE_HIT_3;
import static bowling.domain.rollresult.SpareTest.SPARE_3;
import static bowling.domain.rollresult.StrikeTest.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

public class RollResultsTest {
    public static final RollResults ONE_STRIKE_RESULTS = RollResults.of(STRIKE);
    public static final RollResults DOUBLE_STRIKE_RESULTS = RollResults.of(Arrays.asList(STRIKE, STRIKE));
    public static final RollResults ONE_SPARE_RESULTS = RollResults.of(SPARE_3);
    public static final RollResults MISS_RESULTS = RollResults.of(MISS);
    public static final RollResults GUTTER_RESULTS = RollResults.of(GUTTER);
    public static final RollResults THREE_HIT_RESULTS = RollResults.of(ONE_HIT_3);
    public static final RollResults SPARE_THIRD_THREE_HIT = RollResults.of(Arrays.asList(SPARE_3, ONE_HIT_3));
    public static final RollResults STRIKE_THIRD_THREE_HIT = RollResults.of(Arrays.asList(STRIKE, STRIKE, ONE_HIT_3));

    @Test
    void 결과생성() {
        assertThat(RollResults.of(Miss.of(3, 5))).isEqualTo(RollResults.of(Miss.of(3, 5)));
    }

    @Test
    void 다음결과확인() {
        RollResults result = RollResults.of(OneHit.of(3));
        assertThat(result.next(Pin.of(1, 7), HitNumber.of(3))).isEqualTo(RollResults.of(Miss.of(3, 3)));
    }

    @Test
    void 마무리하였는지확인() {
        assertThat(RollResults.of(Strike.of()).isCleared()).isTrue();
        assertThat(RollResults.of(Spare.of(3)).isCleared()).isTrue();
        assertThat(RollResults.of(Miss.of(3, 3)).isCleared()).isFalse();
        assertThat(RollResults.of(Gutter.of()).isCleared()).isFalse();
    }
}
