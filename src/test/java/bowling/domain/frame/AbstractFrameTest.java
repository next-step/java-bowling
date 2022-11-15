package bowling.domain.frame;

import bowling.domain.state.FrameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new AbstractFrame(new FinalFrame()) {

            @Override
            public boolean isEnded() {
                return false;
            }

            @Override
            public boolean hasScore() {
                return false;
            }

            @Override
            public int getScore() {
                return 0;
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

        assertThat(frame.getState(0)).isEqualTo(FrameState.GUTTER);
        assertThat(frame.getState(1)).isEqualTo(FrameState.SPARE);
        assertThat(frame.getState(2)).isEqualTo(FrameState.STRIKE);
    }

}
