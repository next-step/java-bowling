package bowling.rollresult;

import bowling.HitNumber;
import bowling.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RollResultTest {

    @Test
    void 결과생성() {
        assertThat(RollResult.of(Gutter.of(3, 5))).isEqualTo(RollResult.of(Gutter.of(3, 5)));
    }

    @Test
    void 다음결과확인() {
        RollResult result = RollResult.of(OneRollResultType.of(3));
        assertThat(result.next(Pin.of(1, 7), HitNumber.of(3))).isEqualTo(RollResult.of(Gutter.of(3, 3)));
    }

    @Test
    void 마무리하였는지확인() {
        assertThat(RollResult.of(Strike.of()).isFinished()).isTrue();
        assertThat(RollResult.of(Spare.of(3)).isFinished()).isTrue();
        assertThat(RollResult.of(Gutter.of(3, 3)).isFinished()).isFalse();
        assertThat(RollResult.of(Miss.of()).isFinished()).isFalse();
    }

}
