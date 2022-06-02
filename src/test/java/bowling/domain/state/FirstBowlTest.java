package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {
    @Test
    void 추가점수_남은횟수가_한번_남음_스페어후_다음첫시구() {
        assertThat(new FirstBowl(3).calculateAddScore(new Score(10,1)))
                .isEqualTo(new Score(13,0));
    }

}