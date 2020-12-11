package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameFactoryTest {
    public static final Frame FINAL;
    public static final List<Frame> FRAME_LIST;

    static {
        FRAME_LIST = new ArrayList<>();
        Frame frame = Frame.createFirst();
        FRAME_LIST.add(frame);
        for (int i = 1; i < 10; i++) {
            frame = frame.createNext();
            FRAME_LIST.add(frame);
        }
        FINAL = FRAME_LIST.get(FRAME_LIST.size() - 1);
    }

    @Test
    void create() {
        // when
        final Frame frame = FrameFactory.creates();

        // then
        assertThat(frame).isNotNull();
    }
    
}
