package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {
    @DisplayName("FirstBowl 생성")
    @Test
    void create() {
        FirstBowl firstBowl = new FirstBowl(1);
        assertThat(firstBowl).hasToString("1");
    }

    @DisplayName("FirstBowl 투구 - Miss")
    @Test
    void pitch_miss() {
        FirstBowl firstBowl = new FirstBowl(1);
        State state = firstBowl.pitch(3);

        assertThat(state).isInstanceOf(Miss.class).hasToString("1|3");
    }

    @DisplayName("FirstBowl 투구 - Spare")
    @Test
    void pitch_spare() {
        FirstBowl firstBowl = new FirstBowl(1);
        State state = firstBowl.pitch(9);

        assertThat(state).isInstanceOf(Spare.class).hasToString("1|/");
    }
}
