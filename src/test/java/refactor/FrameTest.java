package refactor;

import org.junit.jupiter.api.Test;

public class FrameTest {
    @Test
    void frame() {
        Frame frame = new Frame();
        Frame result = frame.pitches();
        System.out.println(result);
    }
}
