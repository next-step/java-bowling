package bowling;

import bowling.domain.Bowling;
import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    @Test
    @DisplayName("볼링 객체를 통해 프레임 생성 여부 테스트")
    void containsFrameByBowlingTest() {
        Bowling bowling = new Bowling();
        bowling.go(5);
        Frame frame = new Frame(5, 1);
        assertThat(bowling.isContains(frame)).isTrue();
    }
}
