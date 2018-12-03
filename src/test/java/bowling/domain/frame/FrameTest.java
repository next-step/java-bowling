package bowling.domain.frame;

import org.junit.Test;

public class FrameTest {

    @Test(expected = IllegalArgumentException.class)
    public void 프레임_10개_이상_생성하려는_경우() {
        Frame.generateNextFrame(11);
    }
}
