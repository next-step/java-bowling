package bowling.domain.frame;

import bowling.domain.Swing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameStateTest {

    @DisplayName("NormalFrame에서 사용하는 State 테스트")
    @Test
    void normalFrameStateTest() {

        Swing spare = new Swing();
        spare.addScore(1);
        spare.addScore(9);

        assertEquals(FrameState.SPARE, FrameState.forNormalFrame(spare));

        Swing strike = new Swing();
        strike.addScore(10);

        assertEquals(FrameState.STRIKE, FrameState.forNormalFrame(strike));

        Swing end = new Swing();
        end.addScore(1);
        end.addScore(1);

        assertEquals(FrameState.END, FrameState.forNormalFrame(end));

        Swing running = new Swing();
        end.addScore(5);

        assertEquals(FrameState.RUNNING, FrameState.forNormalFrame(running));
    }

    @DisplayName("LastFrame에서 사용하는 State 테스트")
    @Test
    void lastFrameStateTest() {

        Swing end = new Swing();
        end.addScore(1);
        end.addScore(1);

        assertEquals(FrameState.END, FrameState.forLastFrame(end));

        end = new Swing();
        end.addScore(10);
        end.addScore(10);
        end.addScore(10);

        assertEquals(FrameState.END, FrameState.forLastFrame(end));

        Swing running = new Swing();
        running.addScore(10);
        running.addScore(1);

        assertEquals(FrameState.RUNNING, FrameState.forLastFrame(running));
    }
}
