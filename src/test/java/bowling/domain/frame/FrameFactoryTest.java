package bowling.domain.frame;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameFactoryTest {
    public static final Frame FINAL;

    static {
        Frame frame = Frame.createFirst();
        for (int i = 1; i < 10; i++) {
            frame = frame.createNext();
        }
        FINAL = frame;
    }
    
    public static Frame createFinal() {
        Frame frame = Frame.createFirst();
        for (int i = 1; i < 10; i++) {
            frame = frame.createNext();
        }
        return frame;
    }

    @Test
    void creates_all_frame() {
        // when
        final Frame frame = FrameFactory.creates();

        // then
        assertThat(frame).isNotNull();
    }
    
}
