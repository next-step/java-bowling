package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Primary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by seungwoo.song on 2022-12-05
 */
class LastFrameMissTest {

    @Test
    void 생성() {
        State firstBowl = new LastFrameFirstBowl(4);
        State next = firstBowl.next(PinCount.of(2));

        assertThat(next).isInstanceOf(LastFrameMiss.class);
        assertThat(next.getDesc()).isEqualTo("4|2");
        assertThat(next.getScore()).isEqualTo(new Score(6, 0));
    }
}