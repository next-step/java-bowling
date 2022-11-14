package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("다음 1번의 투구를 가지고 있으면, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(9);
        assertThat(BowlingGameHitState.SPARE.hasScore(frame1)).isFalse();

        frame1.add(1);
        assertThat(BowlingGameHitState.SPARE.hasScore(frame1)).isFalse();

        frame2.add(10);
        assertThat(BowlingGameHitState.SPARE.hasScore(frame1)).isTrue();
    }

    @DisplayName("다음 1번의 투구 점수를 더한 점수를 가지고 있어야 한다.")
    @Test
    void calculateScore() {
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(7);
        frame1.add(3);
        frame2.add(10);
        assertThat(BowlingGameHitState.SPARE.calculateScore(frame1)).isEqualTo(20);
    }

}
