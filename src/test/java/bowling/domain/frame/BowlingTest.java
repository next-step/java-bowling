package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @Test
    @DisplayName("볼링 상태 사이즈")
    void getSizeTest() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.add(new NormalFrame(1));
        Bowling bowling = new Bowling(frames, new Player("KSJ"));
        bowling.bowl(new Pin(5));
        bowling.bowl(new Pin(2));

        bowling.bowl(new Pin(2));
        bowling.bowl(new Pin(3));

        LinkedList<Frame> bowlingStates = bowling.getFrames();

        assertThat(bowlingStates.size()).isEqualTo(3);
    }
}
