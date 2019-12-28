package retry;

import bowling.retry.Bowling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    @Test
    @DisplayName("볼링 프레임 사이즈 테스트")
    void checkSizeFrame() {
        Bowling bowling = new Bowling();
        bowling.go(5);

        assertThat(bowling.getFrames().size()).isEqualTo(1);

        bowling.go(5);

        assertThat(bowling.getFrames().size()).isEqualTo(1);

        bowling.go(3);
        assertThat(bowling.getFrames().size()).isEqualTo(2);
    }
}
