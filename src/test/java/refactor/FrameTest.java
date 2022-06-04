package refactor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    @Test
    void 각라운드는socre0remaining2에서시작() {
        Frame frame = new Frame();
        assertThat(frame).isEqualTo(new Frame(0, 2));
    }

    @Test
    void pitch한번하면remaning은1차감() {
        Frame frame = new Frame();
        Frame actual = frame.pitchManual(2);
        assertThat(actual).isEqualTo(new Frame(2, 1));
    }

    @Test
    void score는10을넘을수없음() {
        assertThatThrownBy(() -> new Frame(11, 1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Frame(9, 1).pitchManual(2)).isInstanceOf(IllegalArgumentException.class);
    }
}
