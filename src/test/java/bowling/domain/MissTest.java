package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @DisplayName("다음 투구를 가지고 있지 않아도, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        BowlingGameFrame frame = new NormalBowlingGameFrame(null);
        frame.add(8);
        assertThat(BowlingGameHitState.MISS.hasScore(frame)).isFalse();

        frame.add(1);

        assertThat(BowlingGameHitState.MISS.hasScore(frame)).isTrue();
    }

}
