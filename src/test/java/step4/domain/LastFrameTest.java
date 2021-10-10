package step4.domain;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import step4.domain.state.State;

class LastFrameTest {
    @Test
    void test() {
        LastFrame frame = new LastFrame(10);
        frame.throwBowl(10);
        frame.throwBowl(10);
        frame.throwBowl(10);
        LinkedList<State> states = frame.getStates();
        System.out.println(states);



    }
}