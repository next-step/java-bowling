package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {
    @Test
    void 추가점수_남은횟수가_한번_남음_스페어후_다음_첫투구() {
        assertThat(new FirstBowl(new Pins(3)).calculateScore(new Score(10,1)))
                .isEqualTo(new Score(13,0));
    }
}