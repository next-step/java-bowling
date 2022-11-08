package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractBowlingGameFrameTest {

    private BowlingGameFrame frame;

    @BeforeEach
    void setUp() {
        frame = new AbstractBowlingGameFrame() {

            @Override
            public boolean isEnded() {
                return false;
            }

        };
    }

    @DisplayName("스트라이크라면, true를 반환해야 한다.")
    @Test
    void isStrike() {
        frame.add(10);
        assertThat(frame.isStrike()).isTrue();
    }

    @DisplayName("스페어라면, true를 반환해야 한다.")
    @Test
    void isSpare() {
        frame.add(2);
        frame.add(8);
        assertThat(frame.isSpare()).isTrue();
    }

    @DisplayName("미스라면, true를 반환해야 한다.")
    @Test
    void isMiss() {
        frame.add(0);
        frame.add(2);
        assertThat(frame.isMiss()).isTrue();
    }

    @DisplayName("남아 있는 핀의 개수를 반환해야 한다.")
    @Test
    void getRemainedPins() {
        frame.add(2);
        frame.add(3);
        assertThat(frame.getRemainedPins()).isEqualTo(5);
    }

}
