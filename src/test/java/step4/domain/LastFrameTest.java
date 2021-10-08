package step4.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import step4.domain.state.Ready;
import step4.domain.state.State;
import step4.view.ResultView;

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