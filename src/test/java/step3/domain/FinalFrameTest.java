package step3.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinalFrameTest {
    @Test
    void test() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(4);
        frame.bowl(6);
        System.out.println(frame.getState().symbol());
    }

    @Test
    void test2() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);
        frame.getStates().stream()
            .forEach(frame1 -> System.out.print(frame1.symbol()));

    }
}