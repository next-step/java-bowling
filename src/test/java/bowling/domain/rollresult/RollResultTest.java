package bowling.domain.rollresult;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RollResultTest {

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
