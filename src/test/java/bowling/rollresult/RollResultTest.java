package bowling.rollresult;

import bowling.RollResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RollResultTest {

    @Test
    void 결과생성() {
        assertThat(RollResult.of(Gutter.of(3, 5))).isEqualTo(RollResult.of(Gutter.of(3, 5)));
    }
}
