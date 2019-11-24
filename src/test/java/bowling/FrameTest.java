package bowling;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("같은 값일 때 생성자 테스트")
    void equalConstructorTest() {
        // give
        Frame frame = new Frame(1, 10);
        Frame frame2 = new Frame(1, 10);
        // when
        boolean isSame = frame.equals(frame2);
        // then
        assertThat(isSame).isTrue();
    }
}
