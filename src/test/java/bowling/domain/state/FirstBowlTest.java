package bowling.domain.state;

import bowling.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    @Test
    @DisplayName("스페어 처리")
    void spare() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);

        Ready ready = new Ready();
        State firstBowl = ready.bowl(pitch1);
        State newFirstBowl = firstBowl.bowl(pitch2);

        assertThat(newFirstBowl).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("미스 처리")
    void miss() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);

        Ready ready = new Ready();
        State miss = ready.bowl(pitch1);
        State newMiss = miss.bowl(pitch2);

        assertThat(newMiss).isInstanceOf(Miss.class);
    }

}