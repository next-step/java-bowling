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

    @DisplayName("남아 있는 핀의 개수를 반환해야 한다.")
    @Test
    void getRemainedPins() {
        frame.add(2);
        frame.add(3);
        assertThat(frame.getRemainedPins()).isEqualTo(5);
    }

    @DisplayName("투구 결과를 반환해야 한다.")
    @Test
    void getResult() {
        frame.add(0);
        frame.add(10);
        frame.add(10);

        assertThat(frame.getResult(0)).isEqualTo(BowlingGameHitResult.GUTTER);
        assertThat(frame.getResult(1)).isEqualTo(BowlingGameHitResult.SPARE);
        assertThat(frame.getResult(2)).isEqualTo(BowlingGameHitResult.STRIKE);
    }

}
